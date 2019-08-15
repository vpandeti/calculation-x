package com.vpandeti.services.calculationservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public final ResponseEntity<ExceptionResponse> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(406, e.getMessage(), "User has already registered, Try logging in with your credentials");
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(404, e.getMessage(), "User is not found with the provided credentials");
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LongestPathCalculationException.class)
    public final ResponseEntity<ExceptionResponse> handleLongestPathCalculationException(LongestPathCalculationException e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(406, e.getMessage(), "Error in calculating longest path for binary tree");
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public final ResponseEntity<ExceptionResponse> handleLongestPathCalculationException(AuthenticationFailedException e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(401, e.getMessage(), "Unauthorized");
        return new ResponseEntity(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }
}
