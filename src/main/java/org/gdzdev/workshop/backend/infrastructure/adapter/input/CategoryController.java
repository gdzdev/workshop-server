package org.gdzdev.workshop.backend.infrastructure.adapter.input;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.gdzdev.workshop.backend.application.dto.CategoryRequest;
import org.gdzdev.workshop.backend.application.dto.CategoryResponse;
import org.gdzdev.workshop.backend.domain.ports.input.CategoryUseCase;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryUseCase useCase;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> searchAll() {
        return ResponseEntity.ok(this.useCase.fetchAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> searchById(@PathVariable Long id) {
        return ResponseEntity.ok(this.useCase.fetchById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CategoryResponse> searchByName(@PathVariable String name) {
        return ResponseEntity.ok(this.useCase.fetchByName(name));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.useCase.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> modify(@PathVariable Long id, @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(this.useCase.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
