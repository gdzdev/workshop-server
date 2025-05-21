package org.gdzdev.workshop.backend.domain.exception;

public class EmptyCartOperationException extends RuntimeException {
    public EmptyCartOperationException(String message) {
        super(message);
    }
}
