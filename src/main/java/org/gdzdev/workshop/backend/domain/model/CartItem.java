package org.gdzdev.workshop.backend.domain.model;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.gdzdev.workshop.backend.domain.enums.ItemType;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItem {

    private Long id;
    private ItemType type;
    private Integer quantity;
    private CartProduct product;
    private Servicing servicing;
    private BigDecimal subTotal;

    public void increaseQuantity() {
        this.quantity++;
    }

    public void decreaseQuantity() {
        if (this.quantity > 1) {
            this.quantity--;
        }
    }

    public BigDecimal getSubTotal() {
        if (type == ItemType.PRODUCT && product != null) {
            return product.getPrice().multiply(BigDecimal.valueOf(quantity));
        } else if (type == ItemType.SERVICE && servicing != null) {
            return servicing.getPrice().multiply(BigDecimal.valueOf(quantity));
        }
        return BigDecimal.ZERO;
    }

}
