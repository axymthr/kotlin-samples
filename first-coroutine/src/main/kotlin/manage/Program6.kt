package manage

import kotlinx.coroutines.*

fun main() = runBlocking {

    val job = withTimeoutOrNull(100) {
        repeat(1000) {
            yield()
            print(".")
            Thread.sleep(1)

        }
    }
    if (job == null) {
        println("timedout")
    }
}