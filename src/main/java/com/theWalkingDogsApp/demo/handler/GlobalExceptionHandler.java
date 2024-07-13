package com.theWalkingDogsApp.demo.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<?> handleException(IllegalStateException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> handleEntityNotFoundException(final EntityNotFoundException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleException(MethodArgumentNotValidException e){
    Set<String> errorMessages = e.getBindingResult().getAllErrors().stream().map(
        DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toSet());
    return ResponseEntity.badRequest().body(errorMessages);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<?> handleException(ConstraintViolationException e){
    Set<String>
        errorMessages = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(
        Collectors.toSet());
    return ResponseEntity.badRequest().body(errorMessages);
  }

  @ExceptionHandler(InvalidFormatException.class)
  public ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, WebRequest request) {
    String errorMessage = "Invalid format: " + ex.getValue() + " is not a valid value for " + ex.getTargetType().getSimpleName();
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
    String errorMessage = "Invalid type: " + ex.getValue() + " is not a valid value for " + ex.getName();
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }


//  @ExceptionHandler(Exception.class)
//  public ResponseEntity<?> handleException(Exception e){
//    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
//  }
}
