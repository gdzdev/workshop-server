package org.gdzdev.workshop.backend.application.dto.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartMessage {

    @JsonProperty("cart_message")
    private String cartMessage;
}
