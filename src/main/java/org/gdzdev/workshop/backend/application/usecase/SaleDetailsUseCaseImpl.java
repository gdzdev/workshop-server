package org.gdzdev.workshop.backend.application.usecase;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.domain.model.Cart;
import org.gdzdev.workshop.backend.domain.model.CartItem;
import org.gdzdev.workshop.backend.domain.model.Sale;
import org.gdzdev.workshop.backend.domain.model.SaleDetail;
import org.gdzdev.workshop.backend.domain.ports.input.SaleDetailsUseCase;
import org.gdzdev.workshop.backend.domain.ports.input.StockUseCase;
import org.gdzdev.workshop.backend.domain.ports.output.SaleDetailRepositoryPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleDetailsUseCaseImpl implements SaleDetailsUseCase {

    private final StockUseCase stockUseCase;
    private final SaleDetailRepositoryPort saleDetailRepository;


    @Override
    public List<SaleDetail> createSaleDetails(Sale sale, List<CartItem> items) {
        List<SaleDetail> saleDetails = items.stream().map(item -> {

            this.stockUseCase.discountStock(item.getProduct().getId(), item.getQuantity());

            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setSale(sale);
            saleDetail.setProduct(item.getProduct());
            saleDetail.setQuantity(item.getQuantity());
            saleDetail.setUnitPrice(item.getProduct().getPrice());
            saleDetail.setSubTotal(
                    (item.getProduct().getPrice() != null ? item.getProduct().getPrice() : BigDecimal.ZERO)
                            .multiply(BigDecimal.valueOf(item.getQuantity() != null ? item.getQuantity() : 0))
            );


            return saleDetail;
        }).collect(Collectors.toList());

        return this.saleDetailRepository.saveAll(saleDetails);
    }
}
