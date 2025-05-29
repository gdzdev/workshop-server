package org.gdzdev.workshop.backend.domain.exception;

public class PurchaseNotFoundException extends RuntimeException{
    public PurchaseNotFoundException(String message) { super(message); }
}
