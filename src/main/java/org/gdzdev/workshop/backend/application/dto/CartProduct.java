package org.gdzdev.workshop.backend.application.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartProduct {

    private Long id;
    private String code;
    private String name;
    private BigDecimal price;
}
