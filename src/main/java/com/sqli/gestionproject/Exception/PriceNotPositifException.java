package com.sqli.gestionproject.Exception;

public class PriceNotPositifException extends RuntimeException{
    public PriceNotPositifException(String message) {
        super(message);
    }
}
