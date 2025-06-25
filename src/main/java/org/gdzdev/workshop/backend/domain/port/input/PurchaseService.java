package org.gdzdev.workshop.backend.domain.port.input;

import org.gdzdev.workshop.backend.application.dto.PaginatedResponse;
import org.gdzdev.workshop.backend.application.dto.purchase.PurchaseRequest;
import org.gdzdev.workshop.backend.application.dto.purchase.PurchaseResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PurchaseService {
    PurchaseResponse createPurchase(PurchaseRequest purchase);
    PurchaseResponse UpdatePurchase(PurchaseRequest purchase);
    boolean deletePurchase(Long id);
    List<PurchaseResponse> getAllPurchases();
    PurchaseResponse getPurchaseById(Long id);
    List<PurchaseResponse> searchPurchase(String provider);
    PaginatedResponse<PurchaseResponse> fetchAllPaginated(Pageable pageable);
}
