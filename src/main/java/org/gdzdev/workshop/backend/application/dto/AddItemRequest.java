package org.gdzdev.workshop.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddItemRequest {

    @JsonProperty("product_id")
    private Long productId;

    private int quantity;
}
