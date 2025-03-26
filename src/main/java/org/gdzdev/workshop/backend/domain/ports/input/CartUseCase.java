package org.gdzdev.workshop.backend.domain.ports.input;

import org.gdzdev.workshop.backend.application.dto.AddItemRequest;
import org.gdzdev.workshop.backend.application.dto.CartResponse;
import org.gdzdev.workshop.backend.domain.model.Cart;

public interface CartUseCase {

    Cart getCart();

    CartResponse addItem(AddItemRequest request);

    CartResponse removeItem(Long itemId);

    void clearCart();

    CartResponse cartTotal();
}
