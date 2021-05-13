package com.example.catalogservice.domain

import org.springframework.data.repository.CrudRepository
import javax.transaction.Transactional


interface BookRepository: CrudRepository<Book, Long> {
    //fun findAll(): Collection<Book>
    fun findByIsbn(isbn: String): Book?
    fun existsByIsbn(isbn: String): Boolean
    //fun save(book: Book): Book
    @Transactional
    fun deleteByIsbn(isbn: String): Unit
}