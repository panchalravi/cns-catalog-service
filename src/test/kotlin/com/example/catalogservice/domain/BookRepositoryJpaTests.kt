package com.example.catalogservice.domain

import com.example.catalogservice.persistence.JpaConfig
import org.assertj.core.api.BDDAssertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.context.annotation.Import
import java.time.Year

@DataJpaTest
@Import(JpaConfig::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryJpaTests {

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Autowired
    private lateinit var entityManager: TestEntityManager

    @Test
    fun findBookByIsbnWhenExisting() {
        val bookIsbn = "1234561235"
        val expectedBook = Book(bookIsbn, "Title", "Author", Year.of(2000), 12.90);
        entityManager.persist(expectedBook);
        val actualBook = bookRepository.findByIsbn(bookIsbn)

        BDDAssertions.then(actualBook).isNotNull
        BDDAssertions.then(actualBook?.isbn).isEqualTo(expectedBook.isbn)
    }
}
