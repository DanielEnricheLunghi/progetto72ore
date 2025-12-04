/***
 ** GlobalExceptionHandler.java
 ** ---------------------------
 ** Questo file rappresenta un gestore globale delle eccezioni per le API REST.
 ** Serve per intercettare errori comuni e restituire risposte uniformi al frontend.
 ** Gestisce:
 ** - Errori di validazione (400 Bad Request)
 ** - Risorse non trovate (404 Not Found)
 ** - Conflitti o duplicati (409 Conflict)
 ** - Errori generici (500 Internal Server Error)
 ***/
package com.condominio.ConvivoApp.condominio.controller; //* Lo mettiamo nello stesso package dei controller

import org.springframework.http.HttpStatus;                  //* Import per codici di stato HTTP
import org.springframework.http.ResponseEntity;             //* Wrapper per risposte HTTP
import org.springframework.web.bind.MethodArgumentNotValidException; //* Eccezione per validazioni fallite
import org.springframework.web.bind.annotation.ExceptionHandler;     //* Annotazione per gestire eccezioni
import org.springframework.web.bind.annotation.RestControllerAdvice; //* Annotazione per controller globale
import java.util.HashMap;                                   //* Per costruire mappe di errori
import java.util.Map;                                       //* Tipo Map per risposte JSON

@RestControllerAdvice                                       //* Indica che questa classe intercetta eccezioni globalmente
public class GlobalExceptionHandler {

    /***
     ** Gestisce errori di validazione (es. campi @NotBlank non rispettati).
     ** Restituisce 400 Bad Request con dettagli dei campi invalidi.
     ***/
    @ExceptionHandler(MethodArgumentNotValidException.class) //* Intercetta eccezioni di validazione
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();        //* Mappa per contenere errori campo → messaggio
        ex.getBindingResult().getFieldErrors().forEach(error -> //* Itera su tutti i campi invalidi
                errors.put(error.getField(), error.getDefaultMessage()) //* Inserisce nome campo e messaggio
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors); //* Risposta 400 con JSON degli errori
    }

    /***
     ** Gestisce IllegalArgumentException (es. condominio non trovato, membership duplicata).
     ** Restituisce 400 Bad Request.
     ***/
    @ExceptionHandler(IllegalArgumentException.class)        //* Intercetta IllegalArgumentException
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, String> error = Map.of("error", ex.getMessage()); //* Costruisce JSON con messaggio
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error); //* Risposta 400
    }

    /***
     ** Gestisce eccezioni generiche non catturate altrove.
     ** Restituisce 500 Internal Server Error.
     ***/
    @ExceptionHandler(Exception.class)                       //* Intercetta qualsiasi altra eccezione
    public ResponseEntity<Map<String, String>> handleGeneric(Exception ex) {
        Map<String, String> error = Map.of("error", "Internal server error"); //* Messaggio generico
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error); //* Risposta 500
    }
}