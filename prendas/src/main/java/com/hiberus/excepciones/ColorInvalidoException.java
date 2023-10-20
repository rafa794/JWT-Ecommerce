package com.hiberus.excepciones;

public class ColorInvalidoException extends RuntimeException {
    public ColorInvalidoException(String message) {
        super(message);
    }
}