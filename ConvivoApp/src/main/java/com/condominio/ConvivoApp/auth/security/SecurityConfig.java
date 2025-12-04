package com.condominio.ConvivoApp.auth.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disabilita CSRF per test
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // tutte le richieste sono libere
                )
                .httpBasic(httpBasic -> httpBasic.disable()) // disabilita Basic Auth
                .formLogin(form -> form.disable()); // disabilita form login

        return http.build();

       /* COMMENTATA PER TESTARE LE API
       http
                .csrf(csrf -> csrf.disable())  // puoi abilitarlo dopo
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers( "/api/auth/**",    // login e register
                                "/public/**",
                                "/error",
                                "/swagger-ui/**",  // Swagger UI
                                "/v3/api-docs/**", // OpenAPI docs JSON/YAML
                                "/swagger-resources/**",
                                "/webjars/**"

                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // o formLogin()

        return http.build();*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
