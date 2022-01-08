package manage

import kotlinx.coroutines.*

fun main() = runBlocking {

    val job = GlobalScope.launch {
        repeat(1000) {
            if (!isActive) return@launch
            print(".")
            Thread.sleep(1)

        }
    }
    delay(100)
    job.cancelAndJoin()
    println("Done")
}