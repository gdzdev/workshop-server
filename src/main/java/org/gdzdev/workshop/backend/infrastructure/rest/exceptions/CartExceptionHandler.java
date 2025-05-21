package org.gdzdev.workshop.backend.infrastructure.rest.exceptions;

import org.gdzdev.workshop.backend.application.dto.ApiResponse;
import org.gdzdev.workshop.backend.domain.exception.CartItemNotFoundException;
import org.gdzdev.workshop.backend.domain.exception.CartNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CartExceptionHandler {

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleCartNotFound(CartNotFoundException exception) {
        return buildErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleCartItemNotFound(CartItemNotFoundException exception) {
        return buildErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponse(String message, HttpStatus status) {
        Map<Object, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("timestamp", LocalDate.now());
        response.put("status", status.value());

        return new ResponseEntity<>(ApiResponse.builder()
                .status("failed")
                .response(response)
                .build(), status);
    }
}
