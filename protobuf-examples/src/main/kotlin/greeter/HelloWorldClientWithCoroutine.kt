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
//        val response = async { stub.sayHello(request) }
//        println("Received: ${response.await().message}")

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
