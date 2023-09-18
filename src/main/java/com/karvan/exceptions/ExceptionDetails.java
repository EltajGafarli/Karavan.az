package com.karvan.exceptions;

import org.springframework.http.HttpStatus;

public record ExceptionDetails(String message, HttpStatus httpStatus) {
}
