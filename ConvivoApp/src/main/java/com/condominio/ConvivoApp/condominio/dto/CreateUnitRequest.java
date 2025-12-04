/***
 ** CreateUnitRequest.java
 ** ----------------------
 ** Questo file rappresenta il DTO per la creazione di una unità immobiliare.
 ** Serve come oggetto di input per il service e il controller.
 ***/
package com.condominio.ConvivoApp.condominio.dto; //* Package dto del modulo condominio

import jakarta.validation.constraints.*; //* Import delle annotazioni di validazione
import lombok.Data;                      //* Lombok: genera getter/setter/equals/hashCode/toString

import java.util.Map;

@Data //* Lombok: genera automaticamente tutti i metodi standard
public class CreateUnitRequest {

    @NotNull //* Il condominio di appartenenza è obbligatorio
    private Long condominiumId; //* ID del condominio a cui appartiene l'unità

    @Size(max = 50) //* Identificativo interno dell'unità (es. "A12"), max 50 caratteri
    private String unitNumber;

    @Size(max = 50) //* Piano dell'unità (es. "2", "T"), max 50 caratteri
    private String floor;

    @Min(1) //* Superficie minima: 1 mq
    private Integer sqM; //* Superficie in metri quadri

    private Map<String, Object> metadata;  //* Metadati JSON come oggetto Java
}


