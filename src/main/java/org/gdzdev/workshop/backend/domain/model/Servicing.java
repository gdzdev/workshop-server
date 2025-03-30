package org.gdzdev.workshop.backend.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Servicing {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}
