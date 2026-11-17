package com.flightreservation.exception;

// Represents an exception thrown when a requested resource cannot be found.
public class ResourceNotFoundException extends RuntimeException{

    // Passes the error message to the parent RuntimeException class.
    public ResourceNotFoundException(String message){
        super(message);
    }
}
