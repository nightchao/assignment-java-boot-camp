package com.exception;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(int userId) {
        super("Address not found userId: " + userId);
    }
}
