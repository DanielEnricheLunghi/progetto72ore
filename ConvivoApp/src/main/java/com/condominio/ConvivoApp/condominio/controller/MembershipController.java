/*******************************************************
 ** MembershipController.java
 ** -----------------------------------------------------
 ** Controller REST per l'entità "UserUnitMembership".
 ** Espone endpoint per:
 ** - Aggiungere una nuova membership
 ** - Ottenere lista di tutte le membership
 ** - Recuperare una membership per ID
 *******************************************************/

package com.condominio.ConvivoApp.condominio.controller; //* Package dei controller del modulo condominio

import com.condominio.ConvivoApp.condominio.dto.AddMembershipRequest;   //* DTO input per aggiungere una membership
import com.condominio.ConvivoApp.condominio.entity.UserUnitMembership;  //* Entità di ritorno
import com.condominio.ConvivoApp.condominio.service.MembershipService;  //* Service con logica di business per membership
import jakarta.validation.Valid;                                        //* Abilita validazioni sul payload JSON
import lombok.RequiredArgsConstructor;                                  //* Lombok: costruttore per campi final
import org.springframework.http.ResponseEntity;                         //* Wrapper per risposte HTTP
import org.springframework.web.bind.annotation.*;                       //* Annotazioni REST per il mapping
import java.util.List;                                                  //* Tipo List per risposte multiple
import java.util.UUID;

@RestController                                      //* Indica controller REST
@RequestMapping("/api/memberships")                  //* Base path per gli endpoint di membership
@RequiredArgsConstructor                             //* Genera costruttore con dipendenze final
public class MembershipController {                  //* Dichiarazione del controller

    private final MembershipService service;         //* Dipendenza verso il service delle membership

    /*******************************************************
     ** Endpoint POST per aggiungere una nuova membership.
     ** - Valida l'input con @Valid.
     ** - Chiama il service per creare la membership.
     *******************************************************/
    @PostMapping
    public ResponseEntity<UserUnitMembership> add(
            @Valid @RequestBody AddMembershipRequest req //* Valida e deserializza il payload in DTO
    ) {
        return ResponseEntity.ok(                     //* Risposta HTTP 200 OK
                service.create(req)                   //* Invoca il metodo "create" del service
        );
    }

    /*******************************************************
     ** Endpoint GET per ottenere tutte le membership.
     *******************************************************/
    @GetMapping
    public ResponseEntity<List<UserUnitMembership>> listAll() {
        return ResponseEntity.ok(                     //* Risposta HTTP 200 OK
                service.list()                        //* Chiama il service per la lista di tutte le membership
        );
    }

    /*******************************************************
     ** Endpoint GET per ottenere una membership specifica.
     ** - Richiede userId e unitId come parametri.
     *******************************************************/
    @GetMapping("/{userId}/{unitId}")
    public ResponseEntity<UserUnitMembership> getMembership(
            @PathVariable UUID userId,                //* Parametro userId dal path
            @PathVariable Long unitId                 //* Parametro unitId dal path
    ) {
        return ResponseEntity.ok(                     //* Risposta HTTP 200 OK
                service.getOrThrow(userId, unitId)    //* Chiama il service per recuperare la membership
        );
    }
}