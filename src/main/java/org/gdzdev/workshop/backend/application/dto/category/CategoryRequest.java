package org.gdzdev.workshop.backend.application.dto.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class CategoryRequest {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 20, message = "El nombre debe tener un máximo de 20 caracteres")
    private String name;

    @NotBlank(message = "La descripcion no puede estar vacia")
    @Size(max = 100, message = "El nombre debe tener un máximo de 100 caracteres")
    private String description;

    @JsonProperty("image_url")
    private String imageUrl;

    MultipartFile file;
}
