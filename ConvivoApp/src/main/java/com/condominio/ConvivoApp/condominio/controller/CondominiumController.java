/**
 ** CondominiumController.java
 ** --------------------------
 ** Controller REST per l'entità "Condominium".
 ** Espone endpoint per:
 ** - Creare un nuovo condominio
 ** - Ottenere la lista di tutti i condomini
 ** - Recuperare un condominio per ID
 **/
package com.condominio.ConvivoApp.condominio.controller; //* Package dei controller del modulo condominio

import com.condominio.ConvivoApp.condominio.dto.CreateCondoRequest; //* Import del DTO di input per la creazione
import com.condominio.ConvivoApp.condominio.entity.Condominium;     //* Import dell'entità usata come output
import com.condominio.ConvivoApp.condominio.service.CondominiumService; //* Import del service che contiene la logica di business
import jakarta.validation.Valid;                                    //* Import per abilitare la validazione dei payload
import lombok.RequiredArgsConstructor;                              //* Lombok: genera il costruttore con le dipendenze final
import org.springframework.http.ResponseEntity;                     //* Wrapper HTTP per gestire status/headers/body
import org.springframework.web.bind.annotation.*;                   //* Import delle annotazioni REST (Controller, Mapping, ecc.)
import java.util.List;                                              //* Import di List per risposte con collezioni

@RestController                                //* Indica che questa classe espone endpoint REST
@RequestMapping("/api/condominiums")           //* Base path per tutti gli endpoint del controller
@RequiredArgsConstructor                       //* Lombok: genera un costruttore con argomenti per i campi final
public class CondominiumController {           //* Dichiarazione della classe del controller

    private final CondominiumService service;  //* Dipendenza verso il service che gestisce i condomini

    @PostMapping                               //* Mappa le richieste HTTP POST su /api/condominiums
    public ResponseEntity<Condominium> create( //* Metodo che gestisce la creazione di un condominio
                                               @Valid @RequestBody CreateCondoRequest req //* @Valid: valida il payload; @RequestBody: deserializza JSON → DTO
    ) {
        return ResponseEntity.ok(              //* Costruisce una risposta HTTP 200 OK
                service.create(req)            //* Invoca la logica di creazione nel service e restituisce l'entità
        );
    }

    @GetMapping                                //* Mappa le richieste HTTP GET su /api/condominiums
    public ResponseEntity<List<Condominium>> list() { //* Metodo che restituisce la lista dei condomini
        return ResponseEntity.ok(              //* Risposta HTTP 200 OK
                service.list()                 //* Chiama il service per ottenere tutti i condomini
        );
    }

    @GetMapping("/{id}")                       //* Mappa GET su /api/condominiums/{id} con path variable
    public ResponseEntity<Condominium> get(    //* Metodo che restituisce un condominio specifico
                                               @PathVariable Long id              // @PathVariable: estrae {id} dall'URL come parametro Long
    ) {
        return ResponseEntity.ok(              //* Risposta HTTP 200 OK
                service.getOrThrow(id)         //* Recupera il condominio o lancia eccezione se non esiste
        );
    }
}