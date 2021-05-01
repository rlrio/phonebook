package com.rlrio.phonebook.exception;

public enum ErrorType {
    USER_NOT_FOUND("User has not been found."),
    CONTACT_NOT_FOUND("Contact has not been found.");

    private final String message;

    ErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
