package com.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(int userId) {
        super("User id: " + userId + " not found");
    }
}
