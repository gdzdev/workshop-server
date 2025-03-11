package org.gdzdev.workshop.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Cart {

    private List<CartItem> cartItems;
    private BigDecimal grandTotal;

    public void calculateGrandTotal() {
        if (cartItems == null || cartItems.isEmpty()) {
            this.grandTotal = BigDecimal.ZERO;
        } else {
            this.grandTotal = cartItems.stream().map(CartItem::getTotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }
}
