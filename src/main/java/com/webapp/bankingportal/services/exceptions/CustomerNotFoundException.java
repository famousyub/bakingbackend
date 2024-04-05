package com.webapp.bankingportal.services.exceptions;
public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
