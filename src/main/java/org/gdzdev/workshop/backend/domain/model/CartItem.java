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
        return subtotal = product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    private void calculateSubTotal() {
        // TODO: check out if method is possible unnecessary implementation
        this.subtotal = product.getPrice()
                .multiply(BigDecimal.valueOf(quantity));
    }
}
