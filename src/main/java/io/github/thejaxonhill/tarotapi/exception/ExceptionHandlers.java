package io.github.thejaxonhill.tarotapi.exception;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(value = { RuntimeException.class })
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(value = { IOException.class })
    protected ResponseEntity<Object> handleIOException(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
