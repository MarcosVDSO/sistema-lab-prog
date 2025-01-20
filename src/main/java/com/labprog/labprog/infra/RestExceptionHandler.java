package com.labprog.labprog.infra;

import com.labprog.labprog.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    private ResponseEntity<String> customerNotFoundHandler(ObjectNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object Not Found");

    }
    @ExceptionHandler(DuplicateEmailException.class)
    private ResponseEntity<String> duplicateEmailHandler(DuplicateEmailException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered");
    }
    @ExceptionHandler(DuplicateUserNameException.class)
    private ResponseEntity<String> duplicateUserNameHandler(DuplicateUserNameException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already registered");
    }
    @ExceptionHandler(InvalidEmailException.class)
    private ResponseEntity<String> invalidEmailHandler(InvalidEmailException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Email");
    }

    @ExceptionHandler(InvalidNameException.class)
    private ResponseEntity<String> invalidNameHandler(InvalidNameException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid First Name or Last Name");
    }

    @ExceptionHandler(InvalidPasswordException.class)
    private ResponseEntity<String> invalidPassWordHandler(InvalidPasswordException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Password");
    }

    @ExceptionHandler(InvalidUserNameException.class)
    private ResponseEntity<String> invalidUserNameHandler(InvalidUserNameException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Username");
    }

    @ExceptionHandler(InvalidAddressException.class)
    private ResponseEntity<String> invalidAddresHandler(InvalidAddressException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Address");
    }

}
