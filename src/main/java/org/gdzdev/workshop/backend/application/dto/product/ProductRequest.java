package org.gdzdev.workshop.backend.application.dto.product;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductRequest {

    @NotBlank(message = "El código no puede estar vacío")
    @Size(max = 36, message = "El código debe tener un máximo de 10 caracteres")
    private String code;

    // TODO: verify that imageUrl field is a image
    @NotNull(message = "La image es requerida.")
    @Size(max = 100, message = "La imagen debe pesar maximo 11mb")
    private String imageUrl;

    @Size(max = 250, message = "El nombre debe tener un máximo de 250 caracteres")
    private String name;

    private MultipartFile file;

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
