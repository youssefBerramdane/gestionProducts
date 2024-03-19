package com.sqli.gestionproject.Exception;

public class DuplicateProductCodeException extends RuntimeException{
    public DuplicateProductCodeException(String message) {
        super(message);
    }
}
