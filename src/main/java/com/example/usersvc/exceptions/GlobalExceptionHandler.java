package com.example.usersvc.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * All field validation failures ---
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> genericException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse();
        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().
                forEach(err -> fieldErrors.put(err.getField(), err.getDefaultMessage()));

        errorResponse.setErrors(fieldErrors);
        errorResponse.setPath(request.getRequestedSessionId());
        errorResponse.setMessage("Validation failed for some or all of the fields!");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setTimestamp(LocalDateTime.now());


        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
