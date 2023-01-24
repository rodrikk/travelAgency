package com.exercise.travelAgency.controllers;

import com.exercise.travelAgency.controllers.travelPkgNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class travelPkgNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(travelPkgNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(travelPkgNotFoundException ex) {
        return ex.getMessage();
    }
}