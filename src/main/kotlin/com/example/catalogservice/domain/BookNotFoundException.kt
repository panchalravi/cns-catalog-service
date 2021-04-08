package com.example.catalogservice.domain

class BookNotFoundException(isbn: String) : RuntimeException("The book with ISBN $isbn was not found")