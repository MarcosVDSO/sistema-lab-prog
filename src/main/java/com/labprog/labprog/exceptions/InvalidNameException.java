package com.labprog.labprog.exceptions;

public class InvalidNameException extends RuntimeException{
    public InvalidNameException() {
        super("Invalid Firstname or Lastname");
    }
    public InvalidNameException(String message) {
        super(message);
    }
}
