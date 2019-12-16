package com.cabinvoicegenerator;

public class CustomException extends Exception {
    enum ExceptionType{
        INVALID_CABRIDETYPE
    }
    ExceptionType type;
    public CustomException(ExceptionType type, String message) {
        super(message);
        this.type=type;
    }
}
