package io.olkkani.dto

import java.time.LocalDateTime

fun main() {
    // java 스타일
    val person1 = PersonDTO()
    person1.setName("acj")
    person1.setAge(10)
    println(person1.getName())
    // kotlin 스타일
    val person2 = PersonDTO()
    person2.name = "acj2"
    println(person2.name)
    // property 가 없음에도 getter method 라면 property 처럼 사용이 가능
    println(person2.uuid)
    // property 는 get 혹은 set 으로 시작해야 정상적으로 사용할 수 있다.
    // person2.myAge = 6 // error
}