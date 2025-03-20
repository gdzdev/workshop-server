package org.gdzdev.workshop.backend.domain.ports.input;

import org.gdzdev.workshop.backend.domain.model.CartItem;
import org.gdzdev.workshop.backend.domain.model.Sale;
import org.gdzdev.workshop.backend.domain.model.SaleDetail;

import java.util.List;

public interface SaleDetailsUseCase {

    List<SaleDetail> createSaleDetails(Sale sale, List<CartItem> cartItems);
}
