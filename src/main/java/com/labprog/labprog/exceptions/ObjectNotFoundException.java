package com.labprog.labprog.exceptions;


public class ObjectNotFoundException extends RuntimeException {

        public ObjectNotFoundException() {
            super("Object not Found");
        }
        public ObjectNotFoundException(String message) {
            super(message);
        }


}
