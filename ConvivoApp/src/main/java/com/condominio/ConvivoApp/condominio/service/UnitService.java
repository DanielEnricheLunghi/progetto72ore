/*******************************************************
 ** UnitService.java
 ** -----------------------------------------------------
 ** Questo file rappresenta il service per l'entità "Unit".
 ** Serve per gestire la logica di business relativa alle unità:
 ** - Creazione di una nuova unità
 ** - Lista di tutte le unità
 ** - Recupero di una unità per ID
 *
 ** Obiettivo:
 ** - Centralizzare la logica di business
 ** - Usare il mapper per conversione DTO → Entity
 ** - Rendere il codice più pulito e manutenibile
 *******************************************************/

package com.condominio.ConvivoApp.condominio.service; //* Package service del modulo condominio

import com.condominio.ConvivoApp.condominio.dto.CreateUnitRequest; //* DTO per la richiesta di creazione
import com.condominio.ConvivoApp.condominio.dto.UnitResponseDTO;
import com.condominio.ConvivoApp.condominio.entity.Condominium;
import com.condominio.ConvivoApp.condominio.entity.Unit;           //* Entità Unit
import com.condominio.ConvivoApp.condominio.repository.CondominiumRepository;
import com.condominio.ConvivoApp.condominio.repository.UnitRepository; //* Repository per CRUD
import com.condominio.ConvivoApp.condominio.mapper.UnitMapper;     //* Mapper per conversione DTO → Entity
import jakarta.validation.Valid;                                   //* Validazione input
import lombok.RequiredArgsConstructor;                             //* Lombok: genera costruttore con dipendenze final
import org.springframework.stereotype.Service;                     //* Indica che è un service Spring
import org.springframework.transaction.annotation.Transactional;   //* Gestione transazioni
import java.util.List;                                             //* Import per liste
import java.util.stream.Collectors;

@Service                                    //* Indica che questa classe è un service Spring
@RequiredArgsConstructor                    //* Lombok: costruttore con repository iniettato
@Transactional                              //* Tutti i metodi sono transazionali
public class UnitService {

    private final UnitRepository unitRepo;  //* Dipendenza: repository per le unità
    private final CondominiumRepository condoRepo;  // Repository per i condomini


    /*******************************************************
     ** Crea una nuova unità.
     ** - Valida l'input con @Valid.
     ** - Usa il mapper per convertire DTO → Entity.
     ** - Salva l'unità nel database.
     *******************************************************/
    public Unit create(@Valid CreateUnitRequest req) {
        Unit unit = UnitMapper.toEntity(req); //* Conversione DTO → Entity tramite mapper
        return unitRepo.save(unit);           //* Salva nel database e restituisce l'entità
    }

    /*******************************************************
     ** Restituisce la lista di tutte le unità.
     *******************************************************/
    @Transactional(readOnly = true)          //* Transazione in sola lettura
    public List<Unit> list() {
        return unitRepo.findAll();           //* Recupera tutte le unità dal DB
    }

    /*******************************************************
     ** Recupera una unità per ID.
     ** - Se non trovata, lancia IllegalArgumentException.
     *******************************************************/
    @Transactional(readOnly = true)          //* Transazione in sola lettura
    public Unit getOrThrow(Long id) {
        return unitRepo.findById(id)         //* Cerca per ID
                .orElseThrow(() -> new IllegalArgumentException("Unit not found")); //* Eccezione se non trovata
    }

    /*******************************************************
     ** Restituisce tutte le unità appartenenti a un condominio.
     ** - Recupera l'entità Condominium dal DB usando l'ID.
     ** - Se non trovata, lancia IllegalArgumentException.
     ** - Usa unitRepo.findByCondominium(condo) per ottenere le unità.
     *******************************************************/
    @Transactional(readOnly = true)
    public List<Unit> listByCondominium(Long condominiumId) {
        Condominium condo = condoRepo.findById(condominiumId) // Cerca il condominio per ID
                .orElseThrow(() -> new IllegalArgumentException("Condominium not found")); // Eccezione se non trovato

        List<Unit> units = unitRepo.findByCondominium(condo); // Recupera tutte le unità legate a quel condominio
        return units;                                         // Restituisce la lista di unità
    }


}