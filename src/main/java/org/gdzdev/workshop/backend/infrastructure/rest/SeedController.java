package org.gdzdev.workshop.backend.infrastructure.rest;

import lombok.RequiredArgsConstructor;

import org.gdzdev.workshop.backend.application.dto.ApiResponse;
import org.gdzdev.workshop.backend.application.usecase.SeedServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin( origins = { "*" }, methods = { RequestMethod.GET })
@RequiredArgsConstructor
@RestController
@RequestMapping("/seeder")
public class SeedController {
    private final SeedServiceImpl _seederUseCase;

    @GetMapping("/seedAll")
    public ResponseEntity<ApiResponse<?>> seedAll() {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResponse
                            .builder()
                            .response(this._seederUseCase.seedALl())
                            .build()
                    );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse
                            .builder()
                            .response(e.getMessage())
                            .build()
                    );
        }
    }

    @GetMapping("/isSeeded")
    public ResponseEntity<ApiResponse<?>> isSeeded() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse
                        .builder()
                        .response("Is seeded 😊")
                        .build()
                );
    }
}

// "Cannot suppress a null exception."