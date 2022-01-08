import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread



fun main() {

//    GlobalScope.launch {
//        delay(1000)
//        println("World")
//    }
//
//    print("Hello, ")
//    Thread.sleep(1500)

    val result = AtomicInteger()
    for(i in 1..1_500_000) {
        GlobalScope.launch {
            result.getAndIncrement()
        }
    }

    Thread.sleep(1000)
    println(result)

//    thread {
//        sleep(1000)
//        println("World")
//    }
//    print("Hello, ")
//
//    Thread.sleep(1500)
}