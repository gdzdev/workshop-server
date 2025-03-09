package org.gdzdev.workshop.backend.domain.exception;

public class ProductNotAvailbleException extends RuntimeException {
    public ProductNotAvailbleException(String message) {
        super(message);
    }
}
