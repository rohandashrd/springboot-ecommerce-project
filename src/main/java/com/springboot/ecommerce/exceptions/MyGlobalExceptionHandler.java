package com.springboot.ecommerce.exceptions;

import com.springboot.ecommerce.payload.APIResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice

public class MyGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> myMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> response = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError)error).getField();
//            String errorMessage = "Name should not be empty"; Add your own custom error message
            String errorMessage = error.getDefaultMessage();
            response.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<String> myResourceNotFoundException(ResourceNotFoundException e)
//    here above we used to have String but we just created a DTO for exceptions too and now will use
//    standard DTO response
    public ResponseEntity<APIResponse> myResourceNotFoundException(ResourceNotFoundException e){
        String message = e.getMessage();
        APIResponse apiResponse = new APIResponse(message,false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIResponse> myAPIException(APIException ap){
        String message = ap.getMessage();
        APIResponse apiResponse = new APIResponse(message,false);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<Map<String, String>> myMConstraintVoliationException(ConstraintViolationException e){
//        Map<String, String> response = new HashMap<>();
//        System.out.println(e.getConstraintViolations());
//        e.getConstraintViolations().forEach(err -> {
//            String fieldName = err.getPropertyPath().toString();
//            String message = err.getMessage();
//            response.put(fieldName, message);
//        });
//        return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
//    }
}
