package com.condominio.ConvivoApp.user.service;

import com.condominio.ConvivoApp.user.dto.UserDTO;
import com.condominio.ConvivoApp.user.entity.Role;
import com.condominio.ConvivoApp.user.entity.RoleName;
import com.condominio.ConvivoApp.user.entity.User;
import com.condominio.ConvivoApp.user.repository.RoleRepository;
import com.condominio.ConvivoApp.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    // Trova utente per email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Trova utente per username
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username).map(u -> (User) u);
    }

    // Crea nuovo utente
    public User createUser(String username, String email, String displayName, String password, Set<RoleName> roles) {
        Set<Role> roleSet = new HashSet<>();
        for (RoleName roleName : roles) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new RuntimeException("Ruolo non trovato: " + roleName));
            roleSet.add(role);
        }

        User user = User.builder()
                .username(username)
                .email(email)
                .displayName(displayName)
                .password(password) // per ora plain text, poi crittografiamo
                .roles(roleSet)
                .build();

        return userRepository.save(user);
    }

    // Lista tutti gli utenti
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    // Aggiorna utente (puoi aggiungere campi da aggiornare)
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // Elimina utente
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User updateUser(UUID id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Aggiorna solo i campi modificabili
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setDisplayName(userDTO.getDisplayName());

        return userRepository.save(user);
    }

    public User createUser(UserDTO userDTO) {

        // Controllo se esiste già un utente con la stessa email
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email already in use: " + userDTO.getEmail());
        }

        User user = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .displayName(userDTO.getDisplayName())
                .password("TEMP")  // oppure null, visto che non usi Security al momento
                .roles(new HashSet<>())
                .build();

        // Se vuoi assegnare un ruolo di default
        // Role roleUser = roleRepository.findByName(RoleName.ROLE_USER)
        //        .orElseThrow(() -> new RuntimeException("Default role not found"));
        // user.getRoles().add(roleUser);

        return userRepository.save(user);
    }


}

