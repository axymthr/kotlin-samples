package context

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val jobs = arrayListOf<Job>()
    createJobs(jobs)
    jobs.forEach { it -> it.join() }
}

private fun CoroutineScope.createJobs(jobs: ArrayList<Job>) {
    jobs += launch {
        println("          'default': In thread ${Thread.currentThread().name}")
    }
    jobs += launch(Default) {
        println("'defaultDispatcher': In thread ${Thread.currentThread().name}")
    }
    jobs += launch(Unconfined) {
        println("'Unconfined': In thread ${Thread.currentThread().name}")
    }
    jobs += launch(coroutineContext) {
        println("'coroutineContext': In thread ${Thread.currentThread().name}")
    }
}