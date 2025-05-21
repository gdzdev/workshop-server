package org.gdzdev.workshop.backend.domain.port.input;

import org.gdzdev.workshop.backend.application.dto.cart.CartMessage;
import org.gdzdev.workshop.backend.application.dto.cart.CartRequest;
import org.gdzdev.workshop.backend.domain.model.Cart;

public interface CartService {

    Cart getActiveCart();

    Cart createCart();

    Integer getItemsCount();

    CartMessage addProductToCart(Long productId);

    CartMessage removeProductFromCart(Long itemId);

    CartMessage increaseItemQuantity(Long productId);

    CartMessage decreaseItemQuantity(Long productId);

    CartMessage emptyCart();
}
