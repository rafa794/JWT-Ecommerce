package com.hiberus.excepciones;

public class TallaInvalidaException extends RuntimeException {
    public TallaInvalidaException(String message) {
        super(message);
    }
}