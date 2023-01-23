package com.exercise.travelAgency;

public class HotelNotFoundException extends RuntimeException{
    HotelNotFoundException(Integer id) {
        super("Could not find hotel " + id);
    }
}
