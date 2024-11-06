package com.example.user_management.Exception;

public enum ErrorCode {
    INVALID_EMAIL("ERR_INVALID_EMAIL"),
    EMPTY_NAME("ERR_EMPTY_NAME"),
    INVALID_PASSWORD("ERR_INVALID_PASSWORD"),
    VALIDATION_ERROR("ERR_VALIDATION_ERROR"),
    USER_NOT_FOUND("ERR_USER_NOT_FOUND"),
    INVALID_TYPE("ERR_INVALID_TYPE"), // New error code for type mismatch
    INTERNAL_ERROR("ERR_INTERNAL_ERROR");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
