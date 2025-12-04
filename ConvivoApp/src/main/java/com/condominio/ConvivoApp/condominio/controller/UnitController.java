/***
 ** UnitController.java
 ** -------------------
 ** Controller REST per l'entità "Unit".
 ** Espone endpoint per:
 ** - Creare una nuova unità
 ** - Ottenere la lista di unità per condominio
 ** - Recuperare un'unità per ID
 ***/
package com.condominio.ConvivoApp.condominio.controller; //* Package dei controller del modulo condominio

import com.condominio.ConvivoApp.condominio.dto.CreateUnitRequest; //* DTO di input per la creazione di unità
import com.condominio.ConvivoApp.condominio.dto.UnitResponseDTO;
import com.condominio.ConvivoApp.condominio.entity.Unit;           //* Entità Unit usata come output
import com.condominio.ConvivoApp.condominio.service.UnitService;   //* Service con la logica di business per le unità
import com.condominio.ConvivoApp.condominio.util.MetadataUtils;
import jakarta.validation.Valid;                                   //* Abilita la validazione dei payload JSON
import lombok.RequiredArgsConstructor;                             //* Lombok: costruttore per campi final
import org.springframework.http.ResponseEntity;                    //* Wrapper per risposte HTTP
import org.springframework.web.bind.annotation.*;                  //* Annotazioni REST
import java.util.List;                                             //* Tipo di ritorno per liste

@RestController                                  //* Classe che espone endpoint REST
@RequestMapping("/api/units")                    //* Base path per gli endpoint relativi alle unità
@RequiredArgsConstructor                         //* Lombok: genera costruttore con dipendenze final
public class UnitController {                    //* Dichiarazione del controller

    private final UnitService service;           //* Dipendenza verso il service delle unità

    @PostMapping //* Mappa HTTP POST su /api/units
    public ResponseEntity<UnitResponseDTO> create(
            @Valid @RequestBody CreateUnitRequest req //* Valida e deserializza il payload in DTO di input
    ) {
        Unit unit = service.create(req);              //* Invoca la creazione nel service e ottiene l'entità JPA
        UnitResponseDTO response = toResponseDTO(unit); //* Converte l'entità in DTO di output
        return ResponseEntity.ok(response);           //* Restituisce il DTO come risposta HTTP 200 OK
    }

    //* Endpoint per ottenere tutte le unità di un condominio
    @GetMapping("/condominium/{condoId}") //* Mappa GET su /api/units/condominium/{condoId}
    public ResponseEntity<List<UnitResponseDTO>> listByCondominium(
            @PathVariable Long condoId //* Parametro preso dal path
    ) {
        List<Unit> units = service.listByCondominium(condoId); //* Recupera le unità dal service
        List<UnitResponseDTO> dtos = units.stream()            //* Converte ogni Unit in DTO
                .map(this::toResponseDTO)
                .toList();
        return ResponseEntity.ok(dtos); // Restituisce lista di DTO come risposta HTTP 200 OK
    }

    //* Endpoint per ottenere una singola unità per ID
    @GetMapping("/{id}") //* Mappa GET su /api/units/{id}
    public ResponseEntity<UnitResponseDTO> get(
            @PathVariable Long id //* Parametro id dal path
    ) {
        Unit unit = service.getOrThrow(id);       //* Recupera l'unità dal service o lancia eccezione
        UnitResponseDTO dto = toResponseDTO(unit); //* Converte l'entità in DTO
        return ResponseEntity.ok(dto);            //* Restituisce il DTO come risposta HTTP 200 OK
    }


    //* Mapper privato per convertire l'entità JPA Unit in DTO di output
    private UnitResponseDTO toResponseDTO(Unit unit) {
        if (unit == null) {
            return null; //* Se l'entità è null, restituisce null
        }

        UnitResponseDTO dto = new UnitResponseDTO();
        dto.setId(unit.getId());                          //* Copia l'ID generato
        dto.setUnitNumber(unit.getUnitNumber());          //* Copia il numero identificativo
        dto.setFloor(unit.getFloor());                    //* Copia il piano
        dto.setSqM(unit.getSqM());                        //* Copia la superficie
        dto.setMetadata(MetadataUtils.parse(unit.getMetadata())); //* Converte la String JSON dell'Entity in Map per il DTO
        dto.setCondominiumId(unit.getCondominium().getId());   //* Copia l'ID del condominio
        dto.setCondominiumName(unit.getCondominium().getName()); //* Copia il nome del condominio
        return dto;
    }
}