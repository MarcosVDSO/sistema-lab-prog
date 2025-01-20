package com.labprog.labprog.exceptions;

public class DuplicateUserNameException extends RuntimeException{
    public DuplicateUserNameException() {
        super("Duplicate User Name");
    }
    public DuplicateUserNameException(String message) {
        super(message);
    }
}
