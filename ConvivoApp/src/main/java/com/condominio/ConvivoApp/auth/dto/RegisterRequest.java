package com.condominio.ConvivoApp.auth.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String displayName;
    private String role; //* "USER" o "ADMIN"
    private String vat;  //* opzionale, obbligatoria se ADMIN
}