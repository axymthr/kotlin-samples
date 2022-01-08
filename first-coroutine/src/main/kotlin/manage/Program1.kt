package manage

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val job = GlobalScope.launch {
        delay(1000)
        println("World")
    }
    print("Hello, ")
    job.join()
}