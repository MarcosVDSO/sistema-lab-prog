package com.labprog.labprog.exceptions;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException() {
        super("Duplicate Email");
    }
    public DuplicateEmailException(String message) {
        super(message);
    }
}
