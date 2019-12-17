package com.cabinvoicegenerator;

public class CustomException extends Exception {

    enum ExceptionType{
        INVALID_CAB_RIDE_TYPE,NULL_USER_ID,RIDE_NOT_FOUND
    }
    ExceptionType type;
    public CustomException(ExceptionType type, String message) {
        super(message);
        this.type=type;
    }

    public CustomException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CustomException(String message, Throwable cause, ExceptionType type) {
        super(message, cause);
        this.type = type;
    }

    public CustomException(Throwable cause, ExceptionType type) {
        super(cause);
        this.type = type;
    }
}
