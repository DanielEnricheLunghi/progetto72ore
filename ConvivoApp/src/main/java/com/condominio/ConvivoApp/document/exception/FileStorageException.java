package com.condominio.ConvivoApp.document.exception;

public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) { super(message); }
    public FileStorageException(String message, Throwable t) { super(message, t); }
}
