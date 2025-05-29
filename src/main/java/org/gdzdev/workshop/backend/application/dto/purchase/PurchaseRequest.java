package org.gdzdev.workshop.backend.application.dto.purchase;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.gdzdev.workshop.backend.domain.model.CartItem;
import org.gdzdev.workshop.backend.domain.model.CartProduct;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseRequest {
    @NotBlank(message = "El campo proveedor es requerido.")
    @Size(min = 3, message = "El proveedor debe ser mayor a 3 caracteres.")
    @Size(max = 100, message = "El proveedor no debe sobre pasar los 100 caracteres.")
    String Provider;

    @NotNull(message = "El campo precio total es requerido.")
    @DecimalMin(value = "0.0", inclusive = false , message = "El precio total debe de ser un numero positivo.")
    BigDecimal totalPrice;

    @NotNull(message = "El campo discount es requerido.")
    @DecimalMin(value = "0.0", inclusive = false , message = "El descuento debe de ser un numero positivo.")
    BigDecimal discount = BigDecimal.ZERO;

    @NotNull(message = "El campo cartItems es requerido.")
    List<CartItem> cartItems;

    @NotNull(message = "El campo cartProducts es requerido.")
    List<CartProduct> cartProducts;
}
