package io.olkkani.issueservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
@SpringBootApplication
class IssueServiceSpringApplication

fun main(args: Array<String>) {
	runApplication<IssueServiceSpringApplication>(*args)
}