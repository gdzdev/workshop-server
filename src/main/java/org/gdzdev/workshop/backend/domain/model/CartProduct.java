package org.gdzdev.workshop.backend.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartProduct {

    private Long id;
    private String code;
    private String name;
    private Integer stock;

    private BigDecimal price;
}
