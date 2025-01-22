package org.example.currencyconverter.util;

public class TooManyRequestsException extends Exception {

    public TooManyRequestsException(String message) {
        super(message);
    }
}
