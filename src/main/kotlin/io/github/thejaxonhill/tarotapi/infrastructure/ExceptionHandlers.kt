package io.github.thejaxonhill.tarotapi.infrastructure

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandlers {
    @ExceptionHandler(value = [RuntimeException::class])
    fun handleRuntimeException(ex: RuntimeException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(ex.toErrorResponse())
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleIOException(ex: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity.internalServerError().body(ex.toErrorResponse())
    }
}

data class ErrorResponse(val message: String? = null, val localizedMessage: String? = null)

private fun Exception.toErrorResponse(): ErrorResponse = ErrorResponse(message, localizedMessage)