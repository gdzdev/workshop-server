package org.gdzdev.workshop.backend.infrastructure.rest.exceptions;

import org.gdzdev.workshop.backend.application.dto.ApiResponse;
import org.gdzdev.workshop.backend.domain.exception.CategoryAlreadyExistsException;
import org.gdzdev.workshop.backend.domain.exception.CategoryNotExistsException;
import org.gdzdev.workshop.backend.domain.exception.CategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CategoryExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handlerCategoryNotFound(CategoryNotFoundException exception) {
        Map<Object, Object> response = new HashMap<>();
        response.put("message", exception.getMessage());
        response.put("timestamp", LocalDate.now());
        response.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(ApiResponse.builder()
                .response(response).status("failed").build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<?>> handlerCategoryAlreadyExists(CategoryAlreadyExistsException exception) {
        Map<Object, Object> response = new HashMap<>();
        response.put("message", exception.getMessage());
        response.put("timestamp", LocalDate.now());
        response.put("status", HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(ApiResponse.builder()
                .response(response).status("failed").build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CategoryNotExistsException.class)
    public ResponseEntity<ApiResponse<?>> handlerCategoryNotExists(CategoryNotExistsException exception) {
        Map<Object, Object> response = new HashMap<>();
        response.put("message cart", exception.getMessage());
        response.put("timestamp", LocalDate.now());
        response.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(ApiResponse.builder()
                .response(response).status("failed").build(), HttpStatus.NOT_FOUND);
    }
}
