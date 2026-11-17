package com.flightreservation.exception;

// Represents an exception caused by a violation of business rules.
public class BusinessException extends RuntimeException{

    public BusinessException (String message){
        super(message);
    }
}
