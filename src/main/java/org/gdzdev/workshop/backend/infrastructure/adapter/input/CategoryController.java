package org.gdzdev.workshop.backend.infrastructure.adapter.input;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.gdzdev.workshop.backend.application.dto.ApiResponse;
import org.gdzdev.workshop.backend.application.dto.CategoryRequest;
import org.gdzdev.workshop.backend.domain.ports.input.CategoryUseCase;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryUseCase useCase;

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

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<?>> searchByName(@PathVariable String name) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.fetchByName(name)).status("success").build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@RequestBody CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder()
                .response(this.useCase.create(request)).status("success").build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> modify(@PathVariable Long id, @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.update(id, request)).status("success").build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
