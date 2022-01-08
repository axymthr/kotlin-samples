package manage

import kotlinx.coroutines.*

fun main() = runBlocking {

    val job = GlobalScope.launch {
        repeat(1000) {

        delay(100)
        println(".")
        }
    }
    delay(2500)
//    job.cancel()
//    job.join()
    job.cancelAndJoin()
    println("Done")
}