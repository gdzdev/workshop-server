package org.gdzdev.workshop.backend.application.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductRequest {
    private String code;
    private String name;
    private String imageUrl;
    private Integer stock;
    private BigDecimal cost;
    private BigDecimal price;
    private Long categoryId;
    private Boolean available;
}
