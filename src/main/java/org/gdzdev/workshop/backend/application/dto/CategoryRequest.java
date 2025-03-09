package org.gdzdev.workshop.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class CategoryRequest {

    private String name;
    private String description;

    @JsonProperty("image_url")
    private String imageUrl;
}
