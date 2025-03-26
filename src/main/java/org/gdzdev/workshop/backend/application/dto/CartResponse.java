package org.gdzdev.workshop.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartResponse {

    @JsonProperty("cart_message")
    private String cartMessage;
}
