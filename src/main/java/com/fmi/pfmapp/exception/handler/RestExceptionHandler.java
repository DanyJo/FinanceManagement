package com.fmi.pfmapp.exception.handler;

import com.fmi.pfmapp.exception.ConditionAlreadySatisfied;
import com.fmi.pfmapp.exception.InsufficientFunds;
import com.fmi.pfmapp.exception.ResourceAlreadyExists;
import com.fmi.pfmapp.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  
    @ExceptionHandler(value = {IllegalArgumentException.class, ResourceAlreadyExists.class,
                               InsufficientFunds.class, ConditionAlreadySatisfied.class})
    protected ResponseEntity<Object> handleBadRequest(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
