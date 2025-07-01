package org.gdzdev.workshop.backend.infrastructure.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdzdev.workshop.backend.application.dto.ApiResponse;
import org.gdzdev.workshop.backend.application.dto.purchase.PurchaseRequest;
import org.gdzdev.workshop.backend.application.usecase.PurchaseServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/purchase")
public class PurchaseController {
    private final PurchaseServiceImpl _useCase;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getPurchaseByPage(@PageableDefault Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse
                        .builder()
                        .response(this._useCase.fetchAllPaginated(pageable))
                        .status("success")
                        .build()
                );
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<?>> newPurchase(@Valid @RequestBody PurchaseRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse
                        .builder()
                        .response(this._useCase.createPurchase(request))
                        .status("success")
                        .build()
                );
    }

    @GetMapping("/search/{provider}")
    public ResponseEntity<ApiResponse<?>> searchPurchase(@Valid @PathVariable String provider) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .response(this._useCase.searchPurchase(provider))
                        .status("success")
                        .build()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getPurchase(@PathVariable Long id) {            return ResponseEntity
            .status(HttpStatus.OK)
            .body(ApiResponse
                    .builder()
                    .response(this._useCase.getPurchaseById(id))
                    .status("success")
                    .build()
            );
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<?>> getAllPurchases() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse
                        .builder()
                        .response(this._useCase.getAllPurchases())
                        .status("success")
                        .build()
                );
    }
}
