package com.exercise.travelAgency;

public class EmailDetailsNotFoundException extends RuntimeException{
    EmailDetailsNotFoundException(Integer id) {
        super("Could not find email " + id);
    }
}
