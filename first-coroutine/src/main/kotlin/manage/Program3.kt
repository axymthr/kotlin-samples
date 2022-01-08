package manage

import kotlinx.coroutines.*

fun main() = runBlocking {

    val job = GlobalScope.launch {
        repeat(1000) {
            print(".")
            yield()
        }
    }
    delay(100)
//    job.cancel()
//    job.join()
    job.cancelAndJoin()
    println("Done")
}