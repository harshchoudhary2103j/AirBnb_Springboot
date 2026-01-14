package com.harshchoudhary.projects.AirBnb_SpringBoot.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
