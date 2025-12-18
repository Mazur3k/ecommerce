package com.example.ecommerce.exceptions.handlers;

import com.example.ecommerce.exceptions.AlreadyExists;
import com.example.ecommerce.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> argumentsNotValid(MethodArgumentNotValidException exc){
        return ResponseEntity.badRequest().body(exc.getBindingResult().getAllErrors().stream().collect(Collectors.toMap(
                        objectError -> ((FieldError) objectError).getField(),
                        objectError -> objectError.getDefaultMessage()
                )
        ));
    }

    @ExceptionHandler
    public ResponseEntity<String> resourceNotFound(ResourceNotFoundException exc){
        return ResponseEntity.badRequest().body(exc.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> constraintViolation(DataIntegrityViolationException exception){
        return ResponseEntity.badRequest().body("Constraint violated");
    }

    @ExceptionHandler
    public ResponseEntity<String> alreadyExists(AlreadyExists exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
