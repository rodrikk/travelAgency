package com.exercise.travelAgency;

public class reservationNotFoundException extends RuntimeException{

    reservationNotFoundException(Integer id) {
        super("Could not find reservation " + id);
    }

}
