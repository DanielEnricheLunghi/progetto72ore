/*******************************************************
 ** CreateUnitResponse.java
 ** -----------------------------------------------------
 ** DTO di OUTPUT per restituire al client i dati di una
 ** unità immobiliare. Usato da UnitMapper.toDto.
 *******************************************************/

package com.condominio.ConvivoApp.condominio.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class CreateUnitResponse {

    private Long id;                        //* Identificativo dell'unità
    private String unitNumber;              //* Numero identificativo (es. "Appartamento 1A")
    private String floor;                   //* Piano dell'unità
    private Integer sqM;                    //* Superficie in metri quadri
    private Map<String,Object> metadata;    //* Metadati JSON come mappa
    private Long condominiumId;             //* FK verso il condominio
    private String condominiumName;         //* Nome del condominio
}