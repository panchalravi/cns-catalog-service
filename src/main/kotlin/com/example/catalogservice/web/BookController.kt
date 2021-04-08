package com.example.catalogservice.web

import com.example.catalogservice.domain.Book
import com.example.catalogservice.domain.BookNotFoundException
import com.example.catalogservice.domain.BookService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("books")
class BookController(private val bookService: BookService) {

    @GetMapping
    fun get() = bookService.viewBookList()

    @GetMapping("{isbn}")
    fun getByIsbn(@PathVariable isbn: String) = bookService.viewBookDetails(isbn)

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun post(@Valid @RequestBody book: Book) = bookService.addBookToCatalog(book)

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable isbn: String) = bookService.removeBookFromCatalog(isbn)

    @PutMapping("{isbn}")
    fun put(@PathVariable isbn: String, @Valid @RequestBody book: Book) = bookService.editBookDetails(isbn, book)
}