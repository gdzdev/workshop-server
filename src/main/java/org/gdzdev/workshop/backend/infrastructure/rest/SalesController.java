package org.gdzdev.workshop.backend.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.application.dto.ApiResponse;
import org.gdzdev.workshop.backend.domain.exception.PurchaseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SalesController {
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<?>> getAllSales() {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse
                        .builder()
                        .status("success")
                        .response(true)
                        .build()
        );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<?>> deleteSale() {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse
                        .builder()
                        .status("success")
                        .response(true)
                        .build()
        );
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<?>> updateSale() {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse
                        .builder()
                        .status("success")
                        .response(true)
                        .build()
        );
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<?>> addSale() {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse
                        .builder()
                        .status("success")
                        .response(true)
                        .build()
        );
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<?>> getAllPaginated() {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse
                        .builder()
                        .status("success")
                        .response(true)
                        .build()
        );
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<?>> searchSale() {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse
                        .builder()
                        .status("success")
                        .response(true)
                        .build()
        );
    }
}
