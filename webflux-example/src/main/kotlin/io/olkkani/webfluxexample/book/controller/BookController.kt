package io.olkkani.webfluxexample.book.controller

import io.olkkani.webfluxexample.book.Book
import io.olkkani.webfluxexample.book.Book2
import io.olkkani.webfluxexample.book.BookRepository
import io.olkkani.webfluxexample.book.BookService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController("/books")
class BookController (
    private val bookService: BookService,
){

    @GetMapping
    fun getAll(): Flux<Book2>{
        return bookService.getAll()
    }
    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): Mono<Book2>{
        return bookService.get(id)
    }
    @PostMapping
    fun add(@RequestBody request: Map<String, Any>): Mono<Book2> {
        return bookService.add(request)
    }
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): Mono<Void> {
        return bookService.delete(id)
    }
//
//    @GetMapping("{name}")
//    fun getByName(@PathVariable name: String): Mono<Book> {
//        return bookService.findByName(name)
//    }
//
//    @PostMapping("/temp")
//    fun create(@RequestBody map: Map<String, Any>): Mono<Book> {
//        val book = Book(
//            name = map["name"].toString(),
//            price = map["price"] as Int
//        )
//        return bookService.save(book)
//    }
}