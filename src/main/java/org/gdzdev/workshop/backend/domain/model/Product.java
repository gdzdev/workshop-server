package org.gdzdev.workshop.backend.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class Product {

    private Long id;
    private String code;
    private String name;
    private String imageUrl;
    private Integer stock;
    private BigDecimal cost;
    private BigDecimal price;
    private Boolean available;
    private Category category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
