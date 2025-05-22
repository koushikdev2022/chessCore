package com.iksen.chessCore.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException ex) {
        if (ex == null) {
            return ResponseEntity.status(400).body(Map.of(
                "status", false,
                "message", "Unknown Authentication Error",
                "status_code", 400
            ));
        }
        
        // Extract error location information
        String errorLocation = extractErrorLocation(ex);
        
        // Enhanced logging with file and line information
        logger.error("Authentication exception at {}: {}", errorLocation, ex.getMessage(), ex);
    
        String errorMessage = (ex.getMessage() != null && !ex.getMessage().isEmpty())
            ? ex.getMessage()
            : ex.getClass().getSimpleName(); // Safe fallback
    
        Map<String, Object> response = new HashMap<>();
        response.put("status", false);
        response.put("message", "Unauthorized access");
        response.put("status_code", 401);
        response.put("error", errorMessage);
        response.put("location", errorLocation);
    
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.computeIfAbsent(error.getField(), key -> new ArrayList<>()).add(error.getDefaultMessage());
        });

        Map<String, Object> response = new HashMap<>();
        response.put("status", false);
        response.put("message", "Validation failed");
        response.put("status_code", 422);
        response.put("errors", errors);
        response.put("location", extractErrorLocation(ex));

        return ResponseEntity.status(422).body(response);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception ex) {
        if (ex == null) {
            return ResponseEntity.status(400).body(Map.of(
                "status", false,
                "message", "Unknown Server Error",
                "status_code", 400
            ));
        }
        
        // Extract error location information
        String errorLocation = extractErrorLocation(ex);
        
        // Enhanced logging with file and line information
        logger.error("Exception occurred at {}: {}", errorLocation, ex.getMessage(), ex);
    
        String errorMessage = (ex.getMessage() != null && !ex.getMessage().isEmpty()) 
            ? ex.getMessage() 
            : ex.getClass().getSimpleName(); // fallback to exception type

        Map<String, Object> response = new HashMap<>();
        response.put("status", false);
        response.put("message", "Something went wrong");
        response.put("status_code", 400);
        response.put("error", errorMessage);
        response.put("location", errorLocation);
        
        return ResponseEntity.status(400).body(response);
    }
    
    /**
     * Handles Java Error types (like compilation errors)
     */
    @ExceptionHandler(Error.class)
    public ResponseEntity<?> handleJavaErrors(Error error) {
        // Extract error location information
        String errorLocation = extractErrorLocation(error);
        
        // Log with detailed information
        logger.error("Critical error occurred at {}: {}", errorLocation, error.getMessage(), error);
        
        String errorMessage = (error.getMessage() != null && !error.getMessage().isEmpty()) 
            ? error.getMessage() 
            : error.getClass().getSimpleName();
            
        Map<String, Object> response = new HashMap<>();
        response.put("status", false);
        response.put("message", "Critical system error");
        response.put("status_code", 400);
        response.put("error", errorMessage);
        response.put("location", errorLocation);
        
        return ResponseEntity.status(400).body(response);
    }
    
    /**
     * Extracts the error location (class, method, file, line) from a Throwable
     */
    private String extractErrorLocation(Throwable throwable) {
        if (throwable == null || throwable.getStackTrace() == null || throwable.getStackTrace().length == 0) {
            return "Unknown location";
        }
        
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        
        // First try to find elements from your application package
        for (StackTraceElement element : stackTrace) {
            if (element.getClassName().startsWith("com.koushik.firstproject")) {
                return formatErrorLocation(element);
            }
        }
        
        // If no application package elements found, return the topmost element
        return formatErrorLocation(stackTrace[0]);
    }
    
    /**
     * Formats a stack trace element into a readable location string
     */
    private String formatErrorLocation(StackTraceElement element) {
        return element.getClassName() + "." + 
               element.getMethodName() + "(" + 
               element.getFileName() + ":" + 
               element.getLineNumber() + ")";
    }
}
