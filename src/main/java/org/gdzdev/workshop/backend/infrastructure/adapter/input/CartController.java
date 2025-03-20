package org.gdzdev.workshop.backend.infrastructure.adapter.input;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.application.dto.AddItemRequest;
import org.gdzdev.workshop.backend.application.dto.CartResponse;
import org.gdzdev.workshop.backend.domain.model.Cart;
import org.gdzdev.workshop.backend.domain.ports.input.CartUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartUseCase cartUseCase;

    @GetMapping
    public ResponseEntity<Cart> getCart() {
        return ResponseEntity.ok(cartUseCase.getCart());
    }

    @GetMapping("/total")
    public ResponseEntity<CartResponse> getCartTotal() {
        return ResponseEntity.ok(this.cartUseCase.cartTotal());
    }

    @PostMapping("/add")
    public ResponseEntity<CartResponse> addItemToCart(@RequestBody AddItemRequest addItemRequest) {
        return ResponseEntity.ok(this.cartUseCase.addItem(addItemRequest));
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<CartResponse> deleteItemFromCart(@PathVariable Long itemId) {
        return ResponseEntity.ok(this.cartUseCase.removeItem(itemId));
    }

    @DeleteMapping("/empty")
    public ResponseEntity<CartResponse> emptyCart() {
        return ResponseEntity.ok(this.cartUseCase.clearCart());
    }

}
