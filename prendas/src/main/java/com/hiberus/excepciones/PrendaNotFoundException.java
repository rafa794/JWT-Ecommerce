package com.hiberus.excepciones;

public class PrendaNotFoundException extends RuntimeException {
    public PrendaNotFoundException(String message) {
        super(message);
    }
}