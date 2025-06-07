package org.gdzdev.workshop.backend.infrastructure.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.application.dto.ApiResponse;
import org.gdzdev.workshop.backend.application.dto.category.CategoryRequest;
import org.gdzdev.workshop.backend.domain.port.input.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"https://simplified-inventory-management.vercel.app", "*"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService useCase;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> searchAll() {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.fetchAll()).status("success").build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> searchById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.fetchById(id)).status("success").build());
    }

    @GetMapping("/name")
    public ResponseEntity<ApiResponse<?>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.fetchByName(name)).status("success").build());
    }

    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<ApiResponse<?>> create(@Valid @ModelAttribute CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder()
                .response(this.useCase.create(request)).status("success").build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> modify(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.update(id, request)).status("success").build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
