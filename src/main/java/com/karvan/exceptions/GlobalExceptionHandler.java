package com.karvan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {RuntimeException.class, UnsupportedOperationException.class, IllegalStateException.class})
    public ResponseEntity<?> handleRuntimeException(Exception exception) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionDetails);
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<?> authenticationException(Exception e) {
        var exceptionDetails = new ExceptionDetails(
                e.getMessage(),
                UNAUTHORIZED);
        return new ResponseEntity<>(exceptionDetails, UNAUTHORIZED);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<?> accessDenied(Exception e) {
        var exceptionDetails = new ExceptionDetails(
                e.getMessage(),
                FORBIDDEN
        );
        return new ResponseEntity<>(exceptionDetails, FORBIDDEN);
    }

}
