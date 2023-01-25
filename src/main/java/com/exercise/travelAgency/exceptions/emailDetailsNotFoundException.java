package com.exercise.travelAgency.exceptions;

public class emailDetailsNotFoundException extends RuntimeException{
    public emailDetailsNotFoundException(Integer id) {
        super("Could not find email " + id);
    }
}
