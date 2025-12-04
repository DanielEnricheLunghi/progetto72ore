/***
 ** CondominiumService.java
 ** -----------------------
 ** Questo file rappresenta il service per l'entità "Condominium".
 ** Serve per gestire la logica di business relativa ai condomini:
 ** - Creazione di un nuovo condominio
 ** - Lista di tutti i condomini
 ** - Recupero di un condominio per ID
 ***/
package com.condominio.ConvivoApp.condominio.service; //* Package service del modulo condominio

import com.condominio.ConvivoApp.condominio.dto.CreateCondoRequest; //* DTO per la richiesta di creazione
import com.condominio.ConvivoApp.condominio.entity.Condominium;     //* Entità Condominium
import com.condominio.ConvivoApp.condominio.repository.CondominiumRepository; //* Repository per CRUD
import com.condominio.ConvivoApp.condominio.util.MetadataUtils;
import jakarta.validation.Valid;                                    //* Validazione input
import lombok.RequiredArgsConstructor;                              //* Lombok: genera costruttore con dipendenze final
import org.springframework.stereotype.Service;                      //* Indica che è un service Spring
import org.springframework.transaction.annotation.Transactional;    //* Gestione transazioni
import java.util.List;                                              //* Import per liste

@Service                                    //* Indica che questa classe è un service Spring
@RequiredArgsConstructor                    //* Lombok: costruttore con repository iniettato
@Transactional                              //* Tutti i metodi sono transazionali
public class CondominiumService {

    private final CondominiumRepository condoRepo; //* Dipendenza: repository per i condomini

    /***
     ** Crea un nuovo condominio.
     ** - Valida l'input con @Valid.
     ** - Controlla se esiste già un condominio con lo stesso nome.
     ** - Se non esiste, salva il nuovo condominio.
     ***/
    public Condominium create(@Valid CreateCondoRequest req) {
        if (condoRepo.existsByName(req.getName())) { //* Controlla se esiste già un condominio con quel nome
            throw new IllegalArgumentException("Condominium name already exists"); //* Eccezione se duplicato
        }
        var c = Condominium.builder()                //* Usa il builder Lombok per creare l'oggetto
                .name(req.getName())                 //* Imposta il nome
                .address(req.getAddress())           //* Imposta l'indirizzo
                .city(req.getCity())                 //* Imposta la città
                .postcode(req.getPostcode())         //* Imposta il CAP
                .metadata(MetadataUtils.serialize(req.getMetadata())) //* Converte la Map in JSON string
                .build();                            //* Costruisce l'oggetto
        return condoRepo.save(c);                    //* Salva nel database e restituisce l'entità
    }

    /***
     ** Restituisce la lista di tutti i condomini.
     ***/
    @Transactional(readOnly = true)                 //* Transazione in sola lettura
    public List<Condominium> list() {
        return condoRepo.findAll();                 //* Recupera tutti i condomini dal DB
    }

    /***
     ** Recupera un condominio per ID.
     ** - Se non trovato, lancia IllegalArgumentException.
     **/
    @Transactional(readOnly = true)                 //* Transazione in sola lettura
    public Condominium getOrThrow(Long id) {
        return condoRepo.findById(id)               //* Cerca per ID
                .orElseThrow(() -> new IllegalArgumentException("Condominium not found")); //* Eccezione se non trovato
    }
}