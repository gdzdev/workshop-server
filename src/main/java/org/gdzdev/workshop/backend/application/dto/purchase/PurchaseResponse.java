package org.gdzdev.workshop.backend.application.dto.purchase;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.gdzdev.workshop.backend.domain.model.CartItem;
import org.gdzdev.workshop.backend.domain.model.CartProduct;
import org.gdzdev.workshop.backend.domain.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseResponse {
    private Long id;
    private String provider;
    private int count;
    private BigDecimal totalPrice;
    private BigDecimal discount;
    private List<CartItem> cartItems;
    private List<CartProduct> cartProducts;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
