package io.olkkani

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class StudyKotlinSpringApplication

fun main(args: Array<String>) {
	runApplication<StudyKotlinSpringApplication>(*args)
}


@RestController
class HelloController {
	@GetMapping("/")
	fun sayHello () = "hello, world"
}
