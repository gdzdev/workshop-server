package org.gdzdev.workshop.backend.infrastructure.adapter.input.exception;

import org.gdzdev.workshop.backend.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class InventoryExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("timestamp", LocalDate.now());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotAvailbleException.class)
    public ResponseEntity<Map<String, Object>> productNotFound(ProductNotAvailbleException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", exception.getMessage());
        response.put("timestamp", LocalDate.now());
        response.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, Object>> productNotFound(ProductNotFoundException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", exception.getMessage());
        response.put("timestamp", LocalDate.now());
        response.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<Object, Object>> categoryNotFound(CategoryNotFoundException exception) {
        Map<Object, Object> response = new HashMap<>();
        response.put("message", exception.getMessage());
        response.put("timestamp", LocalDate.now());
        response.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<Map<Object, Object>> categoryAlreadyExists(CategoryAlreadyExistsException exception) {
        Map<Object, Object> response = new HashMap<>();
        response.put("message", exception.getMessage());
        response.put("timestamp", LocalDate.now());
        response.put("status", HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Map<Object, Object>> cartNotFound(CartNotFoundException exception) {
        Map<Object, Object> response = new HashMap<>();
        response.put("message cart", exception.getMessage());
        response.put("timestamp", LocalDate.now());
        response.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
