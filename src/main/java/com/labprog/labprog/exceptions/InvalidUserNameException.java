package com.labprog.labprog.exceptions;

public class InvalidUserNameException extends RuntimeException{
    public InvalidUserNameException() {
        super("Invalid Username");
    }
    public InvalidUserNameException(String message) {
        super(message);
    }

}
