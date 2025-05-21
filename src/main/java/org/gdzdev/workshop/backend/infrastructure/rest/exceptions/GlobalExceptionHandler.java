package org.gdzdev.workshop.backend.infrastructure.rest.exceptions;

import org.gdzdev.workshop.backend.application.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        response.put("message", "Validation error");
        response.put("errors", errors);
        response.put("timestamp", LocalDate.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(ApiResponse.builder()
                .response(response).status("failed").build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<String> handleMultipartException(MultipartException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Error: La solicitud debe ser multipart/form-data");
    }
    
}
