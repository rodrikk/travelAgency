package com.exercise.travelAgency.exceptions;

public class EmailDetailsNotFoundException extends RuntimeException{
    public EmailDetailsNotFoundException(Integer id) {
        super("Could not find email " + id);
    }
}
