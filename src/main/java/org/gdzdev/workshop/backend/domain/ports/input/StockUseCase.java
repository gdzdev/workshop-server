package org.gdzdev.workshop.backend.domain.ports.input;

public interface StockUseCase {

    void discountStock(Long productId, int quantity);

    void increaseStock(Long productId, int quantity);
}
