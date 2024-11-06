package com.example.user_management.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errorResponse = new HashMap<>();

        Map<String, Map<String, String>> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            String errorCode = getErrorCodeForField(fieldName, errorMessage);

            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("code", errorCode);
            errorDetails.put("message", errorMessage);

            fieldErrors.put(fieldName, errorDetails);
        });

        errorResponse.put("code", ErrorCode.VALIDATION_ERROR.getCode());
        errorResponse.put("message", "One or more fields have invalid values.");
        errorResponse.put("errors", fieldErrors);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("code", ErrorCode.INVALID_TYPE.getCode()); // Assuming INVALID_TYPE is defined in ErrorCode
        errorResponse.put("message", "Invalid data type for field: " + ex.getName());
        errorResponse.put("details", "Expected type: " + ex.getRequiredType().getSimpleName());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("code", ErrorCode.USER_NOT_FOUND.getCode());
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("code", ErrorCode.INTERNAL_ERROR.getCode());
        errorResponse.put("message", "An unexpected error occurred. Please contact support.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    private String getErrorCodeForField(String fieldName, String errorMessage) {
        switch (fieldName) {
            case "email":
                return ErrorCode.INVALID_EMAIL.getCode();
            case "name":
                return ErrorCode.EMPTY_NAME.getCode();
            case "password":
                return ErrorCode.INVALID_PASSWORD.getCode();
            default:
                return ErrorCode.VALIDATION_ERROR.getCode();
        }
    }
}