package io.olkkani.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


fun main() = runBlocking<Unit> { Unit
 val job1 : Job = launch {
     val elapsedTime = measureTimeMillis {
         delay(150)
     }
     println("async task-1 $elapsedTime ms")
 }
    // job1 이 실행되지 않음
    job1.cancel()

    // start 를 시작하는 순간부터 로직이 시작
    val job2 : Job = launch(start = CoroutineStart.LAZY) {
        val elapsedTime = measureTimeMillis {
            delay(100)
        }
        println("async task-2 $elapsedTime ms")
    }
    println("start task-2")

    job2.start()
}

