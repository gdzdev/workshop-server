package org.gdzdev.workshop.backend.infrastructure.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.web.PageableDefault;
import org.gdzdev.workshop.backend.application.dto.ApiResponse;
import org.gdzdev.workshop.backend.domain.port.input.ProductService;
import org.gdzdev.workshop.backend.application.dto.product.ProductRequest;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = {"https://simplified-inventory-management.vercel.app", "*"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService useCase;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> searchAll() {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.fetchAll()).status("success").build());
    }

    @GetMapping("/available")
    public ResponseEntity<ApiResponse<?>> getAllProductsAvailable() {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.fetchCartProductsAvailable()).status("success").build());
    }

    @GetMapping("/page")
    public ResponseEntity<ApiResponse<?>> getPaginated(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.fetchAllPaginated(pageable)).status("success").build());
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<?>> searchByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.fetchByNameOrCode(keyword)).status("success").build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> searchById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.builder().response(this.useCase.fetchById(id)).status("success").build());
    }

    @PostMapping(consumes= {"multipart/form-data"})
    public ResponseEntity<ApiResponse<?>> create(@Valid @ModelAttribute ProductRequest productRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.builder().response(this.useCase.create(productRequest)).status("success").build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> modify(@PathVariable Long id, @Valid @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.update(id, productRequest)).status("success").build());
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<ApiResponse<?>> updateProductImage(@PathVariable Long id, @RequestParam("image") MultipartFile imageFile) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.updateProductImage(id, imageFile)).status("success").build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.useCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
