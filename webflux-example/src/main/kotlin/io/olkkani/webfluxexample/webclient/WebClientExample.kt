package io.olkkani.webfluxexample.webclient

import io.olkkani.webfluxexample.book.Book
import mu.KotlinLogging
import org.slf4j.LoggerFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

@RestController
class WebClientExample {
    val url = "http://localhost:8080/books"
//    val log = LoggerFactory.getLogger(javaClass)
    private val log = KotlinLogging.logger {}

    // spring5 부터 권장하지 않는 restTemplate 방식
    @GetMapping("/books/block")
    fun getBooksBlockingWay() : List<Book> {
        log.info("start RestTemplate")

        val restTemplate = RestTemplate()
        val response = restTemplate.exchange(url, HttpMethod.GET, null,
            object : ParameterizedTypeReference<List<Book>>() {}
        )
        val result = response.body!!
        log.info("result: {}", result)
        log.info("Finish restTemplate")
        return result
    }

    @GetMapping("/books/nonblock")
    fun getBooksNonBlockingWay(): Flux<Book> {
       val flux = WebClient.create()
            .get()
            .uri(url)
            .retrieve()
            .bodyToFlux(Book::class.java)
            .map{
                log.info ( "result: {}", it )
                it
            }
        log.info { "Finish webClient" }
        return flux
    }
}