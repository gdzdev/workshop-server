package org.gdzdev.workshop.backend.application.dto.product;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductRequest {

    @NotBlank(message = "El código no puede estar vacío")
    @Size(max = 10, message = "El código debe tener un máximo de 10 caracteres")
    private String code;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre debe tener un máximo de 100 caracteres")
    private String name;

    private String imageUrl;

    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @NotNull(message = "El costo no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El costo debe ser mayor a 0")
    private BigDecimal cost;

    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    private BigDecimal price;

    @NotNull(message = "La categoría no puede ser nula")
    private Long categoryId;
}
