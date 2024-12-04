package com.theWalkingDogsApp.demo.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<ExceptionRes<?>> handleException(IllegalStateException e) {
    ExceptionRes<?> response = new ExceptionRes<>(400, "Bad request", null, null );
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ExceptionRes<?>> handleException(final EntityNotFoundException e) {
    ExceptionRes<?> response = new ExceptionRes<>(404, "Not found", e.getMessage(), null );
    return ResponseEntity.status(404).body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionRes<Set<String>>> handleException(MethodArgumentNotValidException e){
    Set<String> errorMessages = e.getBindingResult().getAllErrors().stream().map(
        DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toSet());
    ExceptionRes<Set<String>> response = new ExceptionRes<>(400, "Validation error", "One or more fields in the request are invalid", errorMessages);
    return ResponseEntity.badRequest().body(response);
  }


  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ExceptionRes<Set<String>>> handleException(ConstraintViolationException e){
    Set<String>
        errorMessages = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(
        Collectors.toSet());
    ExceptionRes<Set<String>> response = new ExceptionRes<>(400, "Validation error", "One or more fields in the request are invalid", errorMessages);
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(InvalidFormatException.class)
  public ResponseEntity<Object> handleException(InvalidFormatException ex) {
    String errorMessage = "Invalid format: " + ex.getValue() + " is not a valid value for " + ex.getTargetType().getSimpleName();
    ExceptionRes<?> response = new ExceptionRes<>(400, "Bad request", errorMessage, null );
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ExceptionRes<?>> handleException(MethodArgumentTypeMismatchException ex) {
    String errorMessage = "Invalid type: " + ex.getValue() + " is not a valid value for " + Objects.requireNonNull(
        ex.getRequiredType()).getSimpleName();
    ExceptionRes<?> response = new ExceptionRes<>(400, "Bad request", errorMessage, null );
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<ExceptionRes<?>> handleException(UserAlreadyExistsException ex) {
    ExceptionRes<?> response = new ExceptionRes<>(400, "Bad request", "This user is already registered", null);
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(ForbiddenAccessException.class)
  public ResponseEntity<ExceptionRes<?>> handleException(ForbiddenAccessException ex){
    ExceptionRes<?> response =  new ExceptionRes<>(403, "Forbidden", ex.getMessage(), null );
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ExceptionRes<?>> handleException(BadCredentialsException ex) {
    ExceptionRes<?> response =  new ExceptionRes<>(401, "Unauthorized", ex.getMessage(), null );
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ExceptionRes<?>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
    if (ex.getCause() instanceof InvalidTypeIdException invalidTypeIdException) {
      return handleException(invalidTypeIdException); // Reutiliza tu método específico
    }
    ExceptionRes<?> response = new ExceptionRes<>(400, "Bad request", "Malformed JSON request", null);
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(InvalidTypeIdException.class)
  public ResponseEntity<ExceptionRes<?>> handleException(InvalidTypeIdException ex) {
    String description = "Invalid type. " +  ex.getTypeId() + " is not a valid type."+  " Known types = [recurring, one-time]";
    ExceptionRes<?> response =  new ExceptionRes<>(400, "Bad request", description, null );
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(AuthorizationDeniedException.class)
  public ResponseEntity<ExceptionRes<?>> handleException(AuthorizationDeniedException ex){
    ExceptionRes<?> response = new ExceptionRes<>(401, "Unauthorized", "You need ADMIN role to execute this action", null);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionRes<?>> handleException(Exception ex) {
   log.error("Se ha producido un error inesperado: ", ex);
    ExceptionRes<?> response =  new ExceptionRes<>(500, "Internal Server Error", null, null );
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }



}
