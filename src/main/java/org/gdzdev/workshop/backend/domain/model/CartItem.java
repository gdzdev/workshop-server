package org.gdzdev.workshop.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gdzdev.workshop.backend.application.dto.CartProduct;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private Long id;
    private Integer quantity;
    private CartProduct product;

    public BigDecimal getTotal() {
        if (this.product == null || this.quantity == null) {
            return BigDecimal.ZERO;
        }
        return this.product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
    }

}
