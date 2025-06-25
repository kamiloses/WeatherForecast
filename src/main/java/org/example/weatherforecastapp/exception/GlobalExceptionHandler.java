package org.example.weatherforecastapp.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestClientFetchingException.class)
    public ResponseEntity<String> handleRestClientFetchingException(RestClientFetchingException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(ex.getMessage());
    }



    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleInvalidParams(ConstraintViolationException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Latitude must be between -90 and 90, and longitude between -180 and 180");
    }

}