package org.gdzdev.workshop.backend.infrastructure.adapter.input;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.application.dto.ProductRequest;
import org.gdzdev.workshop.backend.application.dto.ProductResponse;
import org.gdzdev.workshop.backend.domain.ports.input.ProductUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductUseCase useCase;

    @GetMapping
    public ResponseEntity<Iterable<ProductResponse>> searchAll() {
        return ResponseEntity.ok(this.useCase.fetchAll());
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<ProductResponse>> searchByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(this.useCase.searchByNameOrCode(keyword));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> searchById(@PathVariable Long id) {
        return ResponseEntity.ok(this.useCase.fetchById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.useCase.create(productRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> modify(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(this.useCase.update(id, productRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.useCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
