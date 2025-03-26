package org.gdzdev.workshop.backend.infrastructure.adapter.input;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.gdzdev.workshop.backend.application.dto.ApiResponse;
import org.gdzdev.workshop.backend.domain.ports.input.CartUseCase;
import org.gdzdev.workshop.backend.application.dto.AddItemRequest;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartUseCase useCase;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getCart() {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.getCart()).status("success").build());
    }

    @GetMapping("/total")
    public ResponseEntity<ApiResponse<?>> getCartTotal() {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.cartTotal()).status("success").build());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<?>> addItemToCart(@RequestBody AddItemRequest addItemRequest) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.addItem(addItemRequest)).status("success").build());
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<ApiResponse<?>> deleteItemFromCart(@PathVariable Long itemId) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.removeItem(itemId)).status("success").build());
    }

    @DeleteMapping("/empty")
    public ResponseEntity<Void> emptyCart() {
        this.useCase.clearCart();
        return ResponseEntity.noContent().build();
    }
}
