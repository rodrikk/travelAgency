package com.exercise.travelAgency.exceptions;

public class travelPkgNotFoundException extends RuntimeException{

    public travelPkgNotFoundException(Integer id) {
        super("Could not find travel package " + id);
    }

}
