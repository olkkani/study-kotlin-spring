package io.olkkani.webfluxexample.book

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.concurrent.atomic.AtomicInteger

data class Book2(val id:Int, val name: String, val price: Int)

@Service
class BookService (
//    private val bookRepository: BookRepository
){
    private final val nextId = AtomicInteger(0)
    val books = mutableListOf(
        Book2(id = nextId.incrementAndGet(), name = "kotlin in action", price = 30000),
        Book2(id = nextId.incrementAndGet(), name = "HTTP perfect guide", price = 40000)
    )
    fun getAll() : Flux<Book2>{
        return Flux.fromIterable(books)
    }

    fun get(id: Int): Mono<Book2> {
        return Mono.justOrEmpty(books.find {it.id == id})
    }

    fun add(request: Map<String, Any>): Mono<Book2> {
        return Mono.justOrEmpty(request)
            .map { map ->
                val book = Book2(
                    id = nextId.incrementAndGet(),
                    name = map["name"].toString(),
                    price = map["price"] as Int,
                )
                books.add(book)
                book
            }
    }

    fun delete(id: Int): Mono<Void> {
        return Mono.justOrEmpty(books.find { it.id == id })
            .map { books.remove(it) }
            .then()
    }



//    fun findByName(name: String) : Mono<Book> {
//        return bookRepository.findByName(name)
//    }
//
//    fun save(book : Book) : Mono<Book> {
//        return bookRepository.save(book)
//    }
}