package io.olkkani.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() = runBlocking <Unit>{

    val flow = simple()
    // 최종 연산자 처럼 collect 를 사용해야 동작
    flow.collect{ value -> println(value) }
}

fun simple() : Flow<Int> = flow {
    println("Flow started")

    for (i in 1..3){
        delay(100)
        // emit 데이터를 통제
        emit(i)
    }
}