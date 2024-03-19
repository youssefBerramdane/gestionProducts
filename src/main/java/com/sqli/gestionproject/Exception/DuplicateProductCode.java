package com.sqli.gestionproject.Exception;

public class DuplicateProductCode extends RuntimeException{
    public DuplicateProductCode(String message) {
        super(message);
    }
}
