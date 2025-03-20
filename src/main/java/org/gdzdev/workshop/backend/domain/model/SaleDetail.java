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
public class SaleDetail {

    private Long id;

    private Sale sale;
    private CartProduct product;

    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal subTotal;
}
