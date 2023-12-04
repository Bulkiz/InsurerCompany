package com.example.insurercompany.controllers.Advice;

import com.example.insurercompany.exceptions.ObjectHasPolicies;
import com.example.insurercompany.exceptions.ObjectNameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(ObjectNameAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleName(ObjectNameAlreadyExistsException objectNameAlreadyExistsException) {
        return objectNameAlreadyExistsException.getMessage();
    }

    @ExceptionHandler(ObjectHasPolicies.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handlePolicy(ObjectHasPolicies objectHasPolicies) {
        return objectHasPolicies.getMessage();
    }

}
