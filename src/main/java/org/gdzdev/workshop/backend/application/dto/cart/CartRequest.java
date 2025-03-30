package org.gdzdev.workshop.backend.application.dto.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {

    @JsonProperty("item_id")
    private Long itemId;
    private Integer quantity;
}
