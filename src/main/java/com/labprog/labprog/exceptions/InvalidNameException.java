package com.labprog.labprog.exceptions;

public class InvalidNameException extends RuntimeException{
    public InvalidNameException() {
        super("Invalid First Name or Last Name");
    }
    public InvalidNameException(String message) {
        super(message);
    }
}
