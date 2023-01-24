package io.olkkani.dto

import java.time.LocalDateTime

class StudentRequest {
    @JvmField // getter setter 를 만들지 않고 property 를 통해서만 접근할 수 있다 ex) student.name
    var name: String? = null
    var birthDay: LocalDateTime? = null
    val age: Int = 10
    var gradle: String? = null
        private set // private 으로 선언하는 경우 class 내부에서만 setter 를 사용할 수 있다

    fun changeGradle(gradle: String){
        this.gradle = gradle
    }
}