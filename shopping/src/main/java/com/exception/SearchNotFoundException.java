package com.exception;

public class SearchNotFoundException extends RuntimeException {

    public SearchNotFoundException(String search) {
        super("Search not found: " + search);
    }
}
