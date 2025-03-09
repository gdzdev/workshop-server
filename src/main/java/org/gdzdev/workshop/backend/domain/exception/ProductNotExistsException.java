package org.gdzdev.workshop.backend.domain.exception;

public class ProductNotExistsException extends RuntimeException {
    public ProductNotExistsException(String message) {
        super(message);
    }
}
