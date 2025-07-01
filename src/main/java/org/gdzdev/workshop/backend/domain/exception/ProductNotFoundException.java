package org.gdzdev.workshop.backend.domain.exception;

// TODO: Already there is an Exception called ProductNotExistsException
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
