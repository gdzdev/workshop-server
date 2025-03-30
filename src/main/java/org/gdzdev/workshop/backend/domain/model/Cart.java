package org.gdzdev.workshop.backend.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Cart {

    @Builder.Default
    private List<CartItem> cartItems = new ArrayList<>();

    private BigDecimal grandTotal;

    public void calculateGrandTotal() {
        if (cartItems == null || cartItems.isEmpty()) {
            this.grandTotal = BigDecimal.ZERO;
        } else {
            this.grandTotal = cartItems.stream().map(CartItem::getSubTotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

}
