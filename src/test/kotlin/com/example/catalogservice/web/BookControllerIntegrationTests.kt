package com.example.catalogservice.web

import com.example.catalogservice.domain.Book
import org.assertj.core.api.BDDAssertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus
import java.time.Year

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerIntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

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

}
