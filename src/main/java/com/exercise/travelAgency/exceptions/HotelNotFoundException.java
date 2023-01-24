package com.exercise.travelAgency.exceptions;

public class HotelNotFoundException extends RuntimeException{
    public HotelNotFoundException(Integer id) {
        super("Could not find hotel " + id);
    }
}
