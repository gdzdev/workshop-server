package org.gdzdev.workshop.backend.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gdzdev.workshop.backend.domain.enums.CartStatus;
import org.gdzdev.workshop.backend.domain.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Cart {

    private Long id;
    private CartStatus status;
    private BigDecimal grandTotal;

    @Builder.Default
    private List<CartItem> cartItems = new ArrayList<>();

    public void calculateGrandTotal() {
        if (cartItems == null || cartItems.isEmpty()) {
            this.grandTotal = BigDecimal.ZERO;
            System.out.println("total if: " + this.grandTotal);
        } else {
            this.grandTotal = cartItems.stream().map(CartItem::getSubTotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            System.out.println("total else: " + this.grandTotal);
        }
    }

    public void addItem(CartItem item) {
        this.cartItems.stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(item.getProduct().getId()))
                .findFirst()
                .ifPresentOrElse(CartItem::increaseQuantity, () -> this.cartItems.add(item));
        calculateGrandTotal();
    }


    public boolean removeItem(Long itemId) {
        boolean removed = this.cartItems.removeIf(cartItem -> cartItem.getId().equals(itemId));
        calculateGrandTotal();
        return removed;
    }

    public void clear() {
        cartItems.clear();
        grandTotal = BigDecimal.ZERO;
    }

    public Integer getTotalItemsCount() {
        return cartItems.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }
}
