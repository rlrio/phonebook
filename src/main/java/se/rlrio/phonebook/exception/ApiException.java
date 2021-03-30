package se.rlrio.phonebook.exception;

public class ApiException extends RuntimeException {

    public ApiException(ErrorType e) {
        super(e.getMessage());
    }

}
