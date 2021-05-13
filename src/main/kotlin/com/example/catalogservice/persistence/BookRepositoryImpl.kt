package com.example.catalogservice.persistence

import com.example.catalogservice.domain.Book
import com.example.catalogservice.domain.BookRepository
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

//@Repository
class BookRepositoryImpl /*: BookRepository */{

    companion object {
        var books = ConcurrentHashMap<String, Book>()
    }

    /*
    override fun findAll(): Collection<Book> = books.values

    override fun findByIsbn(isbn: String): Book? = books[isbn]

    override fun existsByIsbn(isbn: String): Boolean = books[isbn] != null

    override fun save(book: Book): Book {
        books[book.isbn] = book
        return book
    }

    override fun deleteByIsbn(isbn: String): Unit {
        books.remove(isbn)
    }
    */
}