package org.gdzdev.workshop.backend.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.gdzdev.workshop.backend.domain.model.*;
import org.gdzdev.workshop.backend.application.dto.SaleRequest;
import org.springframework.transaction.annotation.Transactional;
import org.gdzdev.workshop.backend.application.dto.SaleResponse;
import org.gdzdev.workshop.backend.domain.ports.input.CartUseCase;
import org.gdzdev.workshop.backend.domain.ports.input.SaleUseCase;
import org.gdzdev.workshop.backend.infrastructure.mappers.SaleMapper;
import org.gdzdev.workshop.backend.domain.ports.input.SaleDetailsUseCase;
import org.gdzdev.workshop.backend.domain.exception.CartNotFoundException;
import org.gdzdev.workshop.backend.domain.exception.SaleNotFoundException;
import org.gdzdev.workshop.backend.domain.ports.output.SaleRepositoryPort;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SaleUseCaseImpl implements SaleUseCase {

    private final SaleMapper saleMapper;
    private final CartUseCase cartUseCase;
    private final SaleRepositoryPort saleRepository;
    private final SaleDetailsUseCase saleDetailsUseCase;

    @Override
    @Transactional(readOnly = true)
    public List<SaleResponse> fetchAllSales() {
        return this.saleRepository.fetchAllSales();
    }

    @Override
    @Transactional(readOnly = true)
    public Sale fetchSaleById(Long id) {
        return this.saleRepository.fetchSaleById(id)
                .orElseThrow(() -> new SaleNotFoundException("Sale not found"));
    }

    @Override
    @Transactional
    public SaleResponse createSale(SaleRequest request) {
        Cart cart = this.cartUseCase.getCart();
        if (cart.getCartItems().isEmpty()) {
            throw new CartNotFoundException("Cart is empty");
        }

        Sale sale = Sale.builder()
                .customerName(request.getCustomerName())
                .paymentStatus(request.getPaymentStatus())
                .saleDate(LocalDateTime.now())
                .grandTotal(cart.getGrandTotal())
                .build();

        sale = this.saleRepository.createSale(sale);

        List<SaleDetail> saleDetails = saleDetailsUseCase.createSaleDetails(sale, cart.getCartItems());
        
        sale.addDetails(saleDetails);

        this.cartUseCase.clearCart();

        return saleMapper.toSaleResponse(sale);
    }

}
