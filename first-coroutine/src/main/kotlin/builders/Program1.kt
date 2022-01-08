package builders

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {

    GlobalScope.launch {
        delay(1000)
        println("World")
    }
    print("Hello, ")
    runBlocking {

        delay(1500)
    }
}