package org.gdzdev.workshop.backend.infrastructure.rest.exceptions;

import org.gdzdev.workshop.backend.application.dto.ApiResponse;
import org.gdzdev.workshop.backend.domain.exception.PurchaseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@ControllerAdvice
public class PurchaseExceptionHandler {
    @ExceptionHandler(PurchaseNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> purchaseNotFound(PurchaseNotFoundException exception) {
        Map<Object, Object> response = new HashMap<>();
        response.put("message", exception.getMessage());
        response.put("timestamp", LocalDate.now());
        response.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(
                ApiResponse.builder()
                .response(response)
                .status("failed")
                .build(),
                HttpStatus.NOT_FOUND
        );
    }


}
