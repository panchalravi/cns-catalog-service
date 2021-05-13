package com.example.catalogservice.domain

import org.assertj.core.api.BDDAssertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.time.Year
import javax.validation.Validation
import javax.validation.Validator

class BookValidationTests {
    companion object {
        lateinit var validator: Validator

        @BeforeAll
        @JvmStatic
        fun setUp(): Unit {
            val factory = Validation.buildDefaultValidatorFactory()
            validator = factory?.validator!!
        }
    }

    @Test
    fun whenAllFieldsCorrectThenValidationSucceeds(): Unit {
        val book = Book("1234567890", "Title", "Author", Year.of(2000), 9.90, "Polar")
        val violations = validator.validate(book)
        BDDAssertions.then(violations).isEmpty()
    }

    @Test
    fun whenIsbnDefinedButIncorrectThenValidationFails() {
        val book = Book("a234567890", "Title", "Author", Year.of(2000), 9.90, "Polar")
        val violations = validator.validate(book)
        BDDAssertions.then(violations).hasSize(1)
        BDDAssertions.then(violations.iterator().next().message).isEqualTo("The ISBN format must follow the standards ISBN-10 or ISBN-13.")
    }
}