package org.gdzdev.workshop.backend.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.application.dto.ApiResponse;
import org.gdzdev.workshop.backend.application.dto.cart.CartRequest;
import org.gdzdev.workshop.backend.domain.port.input.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService useCase;

    // ✅
    @GetMapping("/active")
    public ResponseEntity<ApiResponse<?>> getActiveCart() {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.getActiveCart()).status("success").build());
    }

    // ✅
    @PostMapping
    public ResponseEntity<ApiResponse<?>> createCart() {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder()
                .response(this.useCase.createCart()).status("success").build());
    }

    @GetMapping("/active/items/count")
    public ResponseEntity<ApiResponse<?>> getItemCount() {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.getItemsCount()).status("success").build());
    }


    @PatchMapping("/active/items")
    public ResponseEntity<ApiResponse<?>> addProductToCart(@RequestParam Long productId) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.addProductToCart(productId)).status("success").build());
    }

    @DeleteMapping("/active/items/{itemId}")
    public ResponseEntity<ApiResponse<?>> removeProductFromCart(@PathVariable Long itemId) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.removeProductFromCart(itemId)).status("success").build());
    }

    @PatchMapping("/active/items/{itemId}/increase")
    public ResponseEntity<ApiResponse<?>> increaseItemQuantity(@PathVariable Long itemId) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.increaseItemQuantity(itemId)).status("success").build());
    }

    @PatchMapping("/active/items/{itemId}/decrease")
    public ResponseEntity<ApiResponse<?>> decreaseItemQuantity(@PathVariable Long itemId) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.decreaseItemQuantity(itemId)).status("success").build());
    }

    @DeleteMapping("/active")
    public ResponseEntity<ApiResponse<?>> emptyCart() {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.emptyCart()).status("success").build());
    }
}
