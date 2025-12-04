package com.condominio.ConvivoApp.document.exception;

public class PermissionDeniedException extends RuntimeException {
    public PermissionDeniedException(String message){ super(message); }
}
