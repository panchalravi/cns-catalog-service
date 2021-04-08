package com.example.catalogservice.domain

import java.time.Year
import javax.validation.constraints.*

data class Book(
    @field:NotBlank(message = "The book ISBN must be defined.")
    @field:Pattern(regexp = "^(97([89]))?\\d{9}(\\d|X)$", message = "The ISBN format must follow the standards ISBN-10 or ISBN-13.")
    val isbn: String,

    @field:NotBlank(message = "The book title must be defined.")
    var title: String,

    @field:NotBlank(message = "The book author must be defined.")
    var author: String,

    @field:PastOrPresent(message = "The book cannot have been published in the future.")
    var publishingYear: Year,

    @field:NotNull(message = "The book price must be defined.")
    @field:Positive(message = "The book price must be greater than zero.")
    var price: Double
)