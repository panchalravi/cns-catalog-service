package com.example.catalogservice.web

import com.example.catalogservice.domain.BookAlreadyExistsException
import com.example.catalogservice.domain.BookNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BookControllerAdvice {

    @ExceptionHandler(BookNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun bookNotFoundHandler(ex: BookNotFoundException) = ex.message

    @ExceptionHandler(BookAlreadyExistsException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun bookAlreadyExistHandler(ex: BookAlreadyExistsException) = ex.message

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): HashMap<String, String> {
        val errorsMap = HashMap<String, String>()
        ex.bindingResult.allErrors.forEach {
            val fieldName = when (it) {
                is FieldError -> it.field
                else -> "unknown"
            }
            val errorMessage = it.defaultMessage
            errorMessage?.let {
                errorsMap[fieldName] = errorMessage
            }
        }
        return errorsMap
    }
}