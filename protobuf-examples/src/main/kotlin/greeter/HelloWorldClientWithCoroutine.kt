// Code from https://github.com/GoogleCloudPlatform/kotlin-samples/blob/main/run/grpc-hello-world-gradle/src/main/kotlin/io/grpc/examples/helloworld/HelloWorldClient.kt
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
package greeter

import greeter.GreeterGrpcKt.GreeterCoroutineStub
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.StatusException
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.runBlocking
import java.io.Closeable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class HelloWorldClientWithCoroutine(val channel: ManagedChannel) : Closeable {
    private val stub = GreeterCoroutineStub(channel)

    fun greet(s: String) = runBlocking {
        val request = helloRequest { name = s }
        try {
            val response = stub.sayHello(request)
            println("Greeter client received: ${response.message}")
        } catch (e: StatusException) {
            println("RPC failed: ${e.status}")
        }
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}

fun main(args: Array<String>) {
    Executors.newFixedThreadPool(10).asCoroutineDispatcher().use { dispatcher ->
        val builder = ManagedChannelBuilder.forTarget("localhost:50051").usePlaintext()

        HelloWorldClientWithCoroutine(
            builder.executor(dispatcher.asExecutor()).build()
        ).use {
            val user = args.singleOrNull() ?: "world"
            it.greet(user)
        }
    }
}
