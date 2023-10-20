package com.hiberus.controladores;

import com.hiberus.excepciones.PrendaNotFoundException;
import com.hiberus.excepciones.TallaInvalidaException;
import com.hiberus.excepciones.ColorInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PrendaNotFoundException.class)
    public ResponseEntity<String> handlePrendaNotFoundException(PrendaNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TallaInvalidaException.class)
    public ResponseEntity<String> handleTallaInvalidaException(TallaInvalidaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ColorInvalidoException.class)
    public ResponseEntity<String> handleColorInvalidoException(ColorInvalidoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Si quieres manejar cualquier otra excepción no controlada, puedes hacerlo así:
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("Ha ocurrido un error interno: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
