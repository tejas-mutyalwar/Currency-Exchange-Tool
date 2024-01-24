package com.currency.CurrencyExchangeTool.exception;

public class ApiServiceException extends Exception{

    public ApiServiceException() {
        super("An unexpected error occurred during the currency API call");
    }

    public ApiServiceException(String message) {
        super(message);
    }

    public ApiServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
