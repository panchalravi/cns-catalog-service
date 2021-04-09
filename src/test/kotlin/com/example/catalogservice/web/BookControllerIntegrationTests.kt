package com.example.catalogservice.web

import com.example.catalogservice.domain.Book
import org.assertj.core.api.BDDAssertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus
import java.time.Year

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerIntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

    @Test
    fun whenGetRequestWithIdThenBookReturned() {
        val bookIsbn = "1231231230"
        val bookToCreate = Book(bookIsbn, "Title", "Author", Year.of(1991), 9.90)
        val expectedBook = restTemplate.postForEntity<Book>("/books", bookToCreate).body
        val response = restTemplate.getForEntity<Book>("/books/$bookIsbn")

        BDDAssertions.then(response.statusCode).isEqualTo(HttpStatus.OK)
        BDDAssertions.then(response.body).isNotNull.usingRecursiveComparison().isEqualTo(expectedBook)
    }

    @Test
    fun whenPostRequestThenBookCreated() {
        val expectedBook = Book("1231231231", "Title", "Author", Year.of(1991), 9.90);
        val responseEntity = restTemplate.postForEntity<Book>("/books", expectedBook)

        with(responseEntity) {
            BDDAssertions.then(statusCode).isEqualTo(HttpStatus.CREATED)
            BDDAssertions.then(body).isNotNull
            BDDAssertions.then(body?.isbn).isEqualTo("1231231231")
        }

    }

    @Test
    fun whenPutRequestThenBookUpdated() {
        val bookIsbn = "1231231232"
        val bookToCreate = Book(bookIsbn, "Title", "Author", Year.of(1991), 9.90)
        val createdBook = restTemplate.postForEntity<Book>("/books", bookToCreate).body
        createdBook?.let { it.publishingYear = Year.of(1990) }
        restTemplate.put("/books/$bookIsbn", createdBook)
        val response = restTemplate.getForEntity<Book>("/books/$bookIsbn")
        BDDAssertions.then(response.statusCode).isEqualTo(HttpStatus.OK)
        BDDAssertions.then(response.body).isNotNull
        BDDAssertions.then(response.body?.publishingYear).isEqualTo(Year.of(1990))
    }

    @Test
    fun whenDeleteRequestThenBookDeleted() {
        val bookIsbn = "1231231233"
        val bookToCreate = Book(bookIsbn, "Title", "Author", Year.of(1973), 9.90)
        restTemplate.postForEntity<Book>("/books", bookToCreate)
        restTemplate.delete("/books/$bookIsbn")
        val response = restTemplate.getForEntity<String>(
            "/books/$bookIsbn"
        )
        BDDAssertions.then(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
        BDDAssertions.then(response.body).isEqualTo("The book with ISBN $bookIsbn was not found.")
    }

}
