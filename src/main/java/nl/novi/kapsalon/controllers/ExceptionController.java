package nl.novi.kapsalon.controllers;

import nl.novi.kapsalon.exceptions.DuplicateNameException;
import nl.novi.kapsalon.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateNameException.class)
    public ResponseEntity<String> handleDuplicateNameException (DuplicateNameException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.valueOf(409));
    }
}
