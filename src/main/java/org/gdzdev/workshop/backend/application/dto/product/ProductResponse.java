package org.gdzdev.workshop.backend.application.dto.product;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String code;
    private String name;
    private String imageUrl;
    private Integer stock;
    private BigDecimal cost;
    private BigDecimal price;
    private String categoryName;
    private Boolean available;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
