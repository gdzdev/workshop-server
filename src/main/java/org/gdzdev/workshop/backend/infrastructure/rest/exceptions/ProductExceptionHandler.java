package org.gdzdev.workshop.backend.infrastructure.rest.exceptions;

import org.gdzdev.workshop.backend.application.dto.ApiResponse;
import org.gdzdev.workshop.backend.domain.exception.ProductNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.gdzdev.workshop.backend.domain.exception.ProductNotFoundException;
import org.gdzdev.workshop.backend.domain.exception.ProductNotAvailbleException;
import org.gdzdev.workshop.backend.domain.exception.ProductAlreadyExistsException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleProductNotFound(ProductNotFoundException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", exception.getMessage());
        response.put("timestamp", LocalDate.now());
        response.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(ApiResponse.builder()
                .response(response).status("failed").build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotAvailbleException.class)
    public ResponseEntity<ApiResponse<?>> handleProductNotAvailable(ProductNotAvailbleException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", exception.getMessage());
        response.put("timestamp", LocalDate.now());
        response.put("status", HttpStatus.NOT_ACCEPTABLE.value());
        return new ResponseEntity<>(ApiResponse.builder()
                .response(response).status("failed").build(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<?>> handleProductAlreadyExists(ProductAlreadyExistsException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", exception.getMessage());
        response.put("timestamp", LocalDate.now());
        response.put("status", HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(ApiResponse.builder()
                .response(response).status("failed").build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductNotExistsException.class)
    public ResponseEntity<ApiResponse<?>> handleProductNotExists(ProductNotExistsException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", exception.getMessage());
        response.put("timestamp", LocalDate.now());
        response.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(ApiResponse.builder()
                .response(response).status("failed").build(), HttpStatus.CONFLICT);
    }
}

