package com.example.catalogservice.domain

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Profile
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.time.Year


@Profile("test-data")
@Component
class BookDataLoader(val bookRepository: BookRepository) {

    @EventListener(ApplicationReadyEvent::class)
    fun loadTestData() {
        val book1 = Book("1234567891", "Northern Lights", "Lyra Silvertongue", Year.of(2011), 15.99, "Polar")
        val book2 = Book("1234567892", "Polar Journey", "Iorek Polarson", Year.of(1993), 20.99, "Manning")
        bookRepository.save(book1)
        bookRepository.save(book2)
    }
}