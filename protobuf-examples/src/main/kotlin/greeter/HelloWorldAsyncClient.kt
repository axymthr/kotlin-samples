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
// Code from https://github.com/grpc/grpc-kotlin/blob/master/examples/client/src/main/kotlin/io/grpc/examples/helloworld/HelloWorldClient.kt
package greeter

import greeter.GreeterGrpcKt.GreeterCoroutineStub
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.io.Closeable
import java.util.concurrent.TimeUnit

class HelloWorldAsyncClient(val channel: ManagedChannel) : Closeable {
    private val stub = GreeterCoroutineStub(channel)

    suspend fun greet(name: String) = runBlocking  {
        val request = helloRequest { this.name = name }
        val response = async { stub.sayHello(request) }
        println("Received: ${response.await().message}")
    }


    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }

}

/**
 * Greeter, uses first argument as name to greet if present;
 * greets "world" otherwise.
 */
suspend fun main(args: Array<String>) {
    val port = 50051

    val channel = ManagedChannelBuilder.forAddress("localhost", port).usePlaintext().build()

    val client = HelloWorldAsyncClient(channel)

    val user = args.singleOrNull() ?: "world"
    client.greet(user)
}
