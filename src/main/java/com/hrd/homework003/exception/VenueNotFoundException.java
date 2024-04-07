package com.hrd.homework003.exception;

public class VenueNotFoundException extends RuntimeException{
    public VenueNotFoundException(String message){
        super(message);
    }
}
