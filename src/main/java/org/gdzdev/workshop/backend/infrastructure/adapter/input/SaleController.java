package org.gdzdev.workshop.backend.infrastructure.adapter.input;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.application.dto.SaleRequest;
import org.gdzdev.workshop.backend.application.dto.SaleResponse;
import org.gdzdev.workshop.backend.domain.ports.input.SaleUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sales")
public class SaleController {

    private final SaleUseCase saleUseCase;

    @GetMapping
    public ResponseEntity<List<SaleResponse>> findAll() {
        return ResponseEntity.ok(this.saleUseCase.fetchAllSales());
    }

    @PostMapping("/process")
    public ResponseEntity<SaleResponse> processSale(@RequestBody SaleRequest saleRequest) {
        return ResponseEntity.ok(this.saleUseCase.createSale(saleRequest));
    }

}
