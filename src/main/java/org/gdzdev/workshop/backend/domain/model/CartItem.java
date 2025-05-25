package org.gdzdev.workshop.backend.domain.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CartItem {
    private Long id;
    private Integer quantity;
    private BigDecimal subtotal;
    private BigDecimal unitPrice;

    private Product product;

    public void increaseQuantity() {
        this.quantity++;
        calculateSubTotal();
    }

    public void decreaseQuantity() {
        if (this.quantity > 1) {
            this.quantity--;
            calculateSubTotal();
        }
    }

    public BigDecimal getSubTotal() {
        if (unitPrice != null && quantity != null) {
            return unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
        return BigDecimal.ZERO;
    }

    private void calculateSubTotal() {
        if (unitPrice != null && quantity != null) {
            this.subtotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
        } else {
            this.subtotal = BigDecimal.ZERO; // Or handle nulls as appropriate
        }
    }
}
