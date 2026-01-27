package com.harshchoudhary.projects.AirBnb_SpringBoot.exception;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message) {
        super(message);
    }
}
