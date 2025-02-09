package com.demo.Gestione_prenotazione.exception;

public class DatiNonValidiException extends RuntimeException {
    public DatiNonValidiException(String message) {
        super(message);
    }
}
