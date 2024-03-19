package com.sqli.gestionproject.Exception;

public class PriceNotPositif extends RuntimeException{
    public PriceNotPositif(String message) {
        super(message);
    }
}
