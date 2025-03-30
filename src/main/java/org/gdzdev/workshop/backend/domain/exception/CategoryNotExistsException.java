package org.gdzdev.workshop.backend.domain.exception;

public class CategoryNotExistsException extends RuntimeException {
    public CategoryNotExistsException(String message) {
        super(message);
    }
}
