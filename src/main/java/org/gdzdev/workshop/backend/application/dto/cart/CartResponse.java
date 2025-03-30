package org.gdzdev.workshop.backend.application.dto.cart;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gdzdev.workshop.backend.domain.model.CartProduct;
import org.gdzdev.workshop.backend.domain.model.ItemType;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CartResponse {

    private Long id;
    private CartProduct product;
    private Integer quantity;
    private ItemType type;
    private BigDecimal subtotal;
}
