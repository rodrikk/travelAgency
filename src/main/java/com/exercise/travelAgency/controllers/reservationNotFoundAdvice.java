package com.exercise.travelAgency.controllers;

import com.exercise.travelAgency.exceptions.reservationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//Class that is used to inform the controller of what to do in the event of a reservationNotFound exception.
@ControllerAdvice
class reservationNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(reservationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(reservationNotFoundException ex) {
        return ex.getMessage();
    }
}