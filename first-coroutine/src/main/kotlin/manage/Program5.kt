package manage

import kotlinx.coroutines.*

fun main() = runBlocking {

    val job = GlobalScope.launch {
        try {
            repeat(1000) {
                yield()
                print(".")
                Thread.sleep(10)

            }
        } catch (e: CancellationException) {
            println()
            println("Cancelled: ${e.message}")
        }
    }
    delay(100)
    job.cancel(CancellationException("Too many jobs"))
    job.join()
    println("Done")
}