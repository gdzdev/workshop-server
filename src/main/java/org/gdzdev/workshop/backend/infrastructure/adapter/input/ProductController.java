package org.gdzdev.workshop.backend.infrastructure.adapter.input;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.gdzdev.workshop.backend.application.dto.ApiResponse;
import org.gdzdev.workshop.backend.application.dto.ProductRequest;
import org.gdzdev.workshop.backend.domain.ports.input.ProductUseCase;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductUseCase useCase;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> searchAll() {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.fetchAll()).status("success").build());
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<?>> searchByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.searchByNameOrCode(keyword)).status("success").build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> searchById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.builder().response(this.useCase.fetchById(id)).status("success").build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.builder().response(this.useCase.create(productRequest)).status("success").build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> modify(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.update(id, productRequest)).status("success").build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.useCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
