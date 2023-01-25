package com.exercise.travelAgency.exceptions;

public class hotelNotFoundException extends RuntimeException{
    public hotelNotFoundException(Integer id) {
        super("Could not find hotel " + id);
    }
}
