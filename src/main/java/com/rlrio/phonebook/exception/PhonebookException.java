package com.rlrio.phonebook.exception;

public class PhonebookException extends RuntimeException {
    public PhonebookException(ErrorType e) {
        super(e.getMessage());
    }
}
