/*
 * Copyright 2020 gRPC authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Code from https://github.com/GoogleCloudPlatform/kotlin-samples/blob/main/run/grpc-hello-world-bidi-streaming/src/main/kotlin/io/grpc/examples/helloworld/HelloWorldClient.kt
package greeter

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.Closeable
import java.util.concurrent.TimeUnit

class HelloWorldBidiClient(private val channel: ManagedChannel): Closeable {
    private val stub: GreeterGrpcKt.GreeterCoroutineStub = GreeterGrpcKt.GreeterCoroutineStub(channel)
    suspend fun sayHello(producer: Flow<HelloRequest>): Unit = coroutineScope {
        stub.sayHelloBidiStream(producer).collect { helloResponse ->
            println(helloResponse.message)
        }
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}

fun main(args: Array<String>) = runBlocking {
    val isRemote = args.size == 1

    val builder = if (isRemote)
        ManagedChannelBuilder.forTarget(args[0].removePrefix("https://") + ":443").useTransportSecurity()
    else
        ManagedChannelBuilder.forTarget("localhost:50051").usePlaintext()

    val client = HelloWorldBidiClient(builder.executor(Dispatchers.Default.asExecutor()).build())

    val user = args.singleOrNull() ?: "world"

    val helloFlow = flow {
        while (true) {
            delay(1000)
            emit(helloRequest { name = user })
        }
    }

    client.sayHello(helloFlow)
}