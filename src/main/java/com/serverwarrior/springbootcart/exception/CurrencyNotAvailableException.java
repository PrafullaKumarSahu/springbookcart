package com.serverwarrior.springbootcart.exception;

public class CurrencyNotAvailableException extends RuntimeException {
    public CurrencyNotAvailableException(String s) {
        super(s);
    }
}
