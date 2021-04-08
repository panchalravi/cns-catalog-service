package com.example.catalogservice.domain

import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository: BookRepository) {

    fun viewBookList(): Collection<Book> = bookRepository.findAll()

    fun viewBookDetails(isbn: String): Book = bookRepository.findByIsbn(isbn) ?: throw BookNotFoundException(isbn)

    fun addBookToCatalog(book: Book): Book {
        if (bookRepository.existsByIsbn(book.isbn)) {
            throw BookAlreadyExistsException(book.isbn)
        }
        return bookRepository.save(book)
    }

    fun removeBookFromCatalog(isbn: String): Unit {
        if(!bookRepository.existsByIsbn(isbn)) {
            throw BookNotFoundException(isbn)
        }
        bookRepository.deleteByIsbn(isbn)
    }

    fun editBookDetails(isbn: String, book: Book): Book {
        val existingBook = bookRepository.findByIsbn(isbn) ?: addBookToCatalog(book)
        existingBook.apply {
            title = book.title
            author = book.author
            publishingYear = book.publishingYear
            price = book.price
        }
        return bookRepository.save(existingBook)
    }

}