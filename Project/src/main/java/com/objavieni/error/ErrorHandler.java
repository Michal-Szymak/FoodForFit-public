package com.objavieni.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PreferencesNotFoundException.class)
    public void addressNotFoundException() { }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public void personNotFoundException() { }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(InvalidApiResponseException.class)
    public void invalidApiResponseException() { }

}
