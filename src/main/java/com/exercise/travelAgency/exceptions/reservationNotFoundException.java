package com.exercise.travelAgency.exceptions;

public class reservationNotFoundException extends RuntimeException{

    public reservationNotFoundException(Integer id) {
        super("Could not find reservation " + id);
    }

}
