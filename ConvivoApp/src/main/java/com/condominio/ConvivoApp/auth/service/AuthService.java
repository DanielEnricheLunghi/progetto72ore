package com.condominio.ConvivoApp.auth.service;

import com.condominio.ConvivoApp.auth.dto.*;
import com.condominio.ConvivoApp.auth.util.JwtUtil;
import com.condominio.ConvivoApp.user.entity.*;
import com.condominio.ConvivoApp.user.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    //* LOGIN
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email non trovata"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Password errata");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);
    }

    //* REGISTER
    public AuthResponse register(RegisterRequest request) {

        //* 1. Controllo email duplicata
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email già in uso");
        }

        //* 2. Recupero ruolo
        Role role = roleRepository.findByName(
                "ADMIN".equalsIgnoreCase(request.getRole()) ? RoleName.ROLE_ADMIN : RoleName.ROLE_USER
        ).orElseThrow(() -> new RuntimeException("Ruolo mancante"));

        //* 3. Validazione displayName
        if (request.getDisplayName() == null || request.getDisplayName().trim().isEmpty()) {
            throw new RuntimeException("Il nome visualizzato è obbligatorio");
        }

        //* 4. Validazione Partita IVA se ADMIN
        if ("ADMIN".equalsIgnoreCase(request.getRole()) &&
                (request.getVat() == null || request.getVat().trim().isEmpty())) {
            throw new RuntimeException("La partita IVA è obbligatoria per gli amministratori");
        }

        //* 5. Creazione utente
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .displayName(request.getDisplayName())
                .roles(Set.of(role))
                .vat("ADMIN".equalsIgnoreCase(request.getRole()) ? request.getVat() : null)
                .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}
