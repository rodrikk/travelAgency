package com.exercise.travelAgency.controllers;

public class travelPkgNotFoundException extends RuntimeException{

    travelPkgNotFoundException(Integer id) {
        super("Could not find travel package " + id);
    }

}
