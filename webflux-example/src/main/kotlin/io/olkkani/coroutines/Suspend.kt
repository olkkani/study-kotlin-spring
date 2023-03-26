package io.olkkani.coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// suspend 는 일시 중단이 가능한 키워드
// 일반 함수를 마음껏 호출할 수 있지만, 일반 함수는 suspend 를 바로 호출 할 수 없다
// coroutineScope 은 runBlocking 과 다르게 thread 가 blocking 되지 않고 coroutine 이 동작
suspend fun doSomeThing() = coroutineScope{

    launch {
        delay(200)
        println("world!")
    }

    launch {
        printHello()
    }
}
// 일반 함수에서 suspend 를 사용하려면 일반 함수 역시 suspend 키워드를 붙이거나 runBlocking 을 사용해야한다
fun main() = runBlocking<Unit>{
    doSomeThing()
}

fun printHello() = println("hello")