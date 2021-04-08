package com.example.catalogservice.web

import com.example.catalogservice.domain.Book
import org.assertj.core.api.BDDAssertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester
import java.time.Year

@JsonTest
class BookJsonTests(@Autowired private val json: JacksonTester<Book>) {

    @Test
    fun testSerialize() {
        val book = Book("1231231231", "Title", "Author", Year.of(1991), 9.90);

        BDDAssertions.then(json.write(book)).extractingJsonPathStringValue("@.isbn").isEqualTo("1231231231")
        BDDAssertions.then(json.write(book)).extractingJsonPathStringValue("@.title").isEqualTo("Title")
        BDDAssertions.then(json.write(book)).extractingJsonPathStringValue("@.author").isEqualTo("Author")
        BDDAssertions.then(json.write(book)).extractingJsonPathStringValue("@.publishingYear").isEqualTo("1991")
        BDDAssertions.then(json.write(book)).extractingJsonPathNumberValue("@.price").isEqualTo(9.90)

    }

    @Test
    fun testDeserialize() {
        val content = "{\n  \"isbn\": \"1234567890\",\n  \"title\": \"Title\",\n  \"author\": \"Author\",\n  \"publishingYear\": \"1991\",\n  \"price\": 9.90\n}\n"
        BDDAssertions.then(json.parseObject(content).isbn).isEqualTo("1234567890")
    }
}