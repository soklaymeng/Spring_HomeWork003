package com.hrd.homework003.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(VenueNotFoundException.class)
    public ProblemDetail handelVenueNotFoundException(VenueNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Not Found");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    //Create Hashmap to restore both field and message
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        HashMap<String, String> errors = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Invalid input field...");
        problemDetail.setTitle("Not Found");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        problemDetail.setProperty("Error", errors);
        return problemDetail;
    }

    //Validation page size
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail methodValidationExceptionHandler(HandlerMethodValidationException e) {
        HashMap<String, String> errors = new HashMap<>();
        //
        for (var parameterError : e.getAllValidationResults()) {
            final String parameterName = parameterError.getMethodParameter().getParameterName();
            for (var error : parameterError.getResolvableErrors()) {
                errors.put(parameterName, error.getDefaultMessage());
            }

        }
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Validation field, Please enter positive number..."
        );
     problemDetail.setTitle("Bad Request...");
     problemDetail.setProperty("Errors..",errors);
     return problemDetail;


    }
}