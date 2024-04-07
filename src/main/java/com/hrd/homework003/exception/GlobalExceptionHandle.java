package com.hrd.homework003.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(VenueNotFoundException.class)
    public ProblemDetail handelVenueNotFoundException(VenueNotFoundException e){
        ProblemDetail problemDetail= ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,e.getMessage());
        problemDetail.setTitle("Not Found");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
return problemDetail;
    }
    //Create Hashmap to restore both field and message
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        HashMap<String, String >errors = new HashMap<>();
        for (FieldError fieldError: e.getBindingResult().getFieldErrors()){
           errors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        ProblemDetail problemDetail= ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,"Invalid input field...");
        problemDetail.setTitle("Not Found");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        problemDetail.setProperty("Error",errors);
        return problemDetail;
    }

}
