package org.gdzdev.workshop.backend.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.application.dto.ApiResponse;
import org.gdzdev.workshop.backend.application.dto.cart.CartRequest;
import org.gdzdev.workshop.backend.domain.port.input.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://simplified-inventory-management.vercel.app")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService useCase;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getCart() {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.getCart()).status("success").build());
    }

    @PostMapping("/add-product")
    public ResponseEntity<ApiResponse<?>> addProductToCart(@RequestBody CartRequest request) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.addProductToCart(request)).status("success").build());
    }

    @PostMapping("/add-service")
    public ResponseEntity<ApiResponse<?>> addServiceToCart(@RequestBody CartRequest request) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.addServicingToCart(request)).status("success").build());
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<ApiResponse<?>> removeProductFromCart(@PathVariable Long itemId) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.removeProductFromCart(itemId)).status("success").build());
    }

    @PatchMapping("/items/{productId}/increase")
    public ResponseEntity<ApiResponse<?>> increaseItemQuantity(@PathVariable Long productId) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.increaseItemQuantity(productId)).status("success").build());
    }

    @PatchMapping("/items/{productId}/decrease")
    public ResponseEntity<ApiResponse<?>> decreaseItemQuantity(@PathVariable Long productId) {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.decreaseItemQuantity(productId)).status("success").build());
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<?>> emptyCart() {
        return ResponseEntity.ok(ApiResponse.builder()
                .response(this.useCase.emptyCart()).status("success").build());
    }
}
