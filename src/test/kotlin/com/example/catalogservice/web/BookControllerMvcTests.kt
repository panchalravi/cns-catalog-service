package com.example.catalogservice.web

import com.example.catalogservice.domain.BookNotFoundException
import com.example.catalogservice.domain.BookService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(BookController::class)
class BookControllerMvcTests(
    @Autowired val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var bookService: BookService

    @Test
    fun whenReadingNotExistingBookThenShouldReturn404() {
        val isbn = "73737313940"
        //every { bookService.viewBookDetails(isbn) } throws BookNotFoundException(isbn)
        BDDMockito.given(bookService.viewBookDetails(isbn)).willThrow(BookNotFoundException(isbn))
        mockMvc.get("/books/$isbn").andExpect {
            status { isNotFound() }
        }
    }
}