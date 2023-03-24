package io.olkkani.webfluxexample.book

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface BookRepository
//interface BookRepository : ReactiveCrudRepository<Book, Long> {
//
//    fun findByName(name: String) : Mono<Book>
//}