package com.labprog.labprog.exceptions;

public class InvalidAddressException extends RuntimeException{
    public InvalidAddressException() {
        super("Invalid Address");
    }
    public InvalidAddressException(String message) {
        super(message);
    }
}
