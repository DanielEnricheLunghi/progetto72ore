/***
 ** UnitResponseDTO.java
 ** --------------------
 ** Questa classe rappresenta un DTO (Data Transfer Object) di OUTPUT.
 ** Serve per restituire al client i dati di una "Unit" in formato JSON pulito e sicuro.
 ** A differenza dell'entità JPA "Unit", qui non ci sono relazioni complesse (es. Set<UserUnitMembership>).
 ** In questo modo evitiamo cicli infiniti e problemi di serializzazione (ConcurrentModificationException).
 ** Il controller mapperà l'entità JPA "Unit" in questo DTO e lo invierà come risposta.
 ***/

package com.condominio.ConvivoApp.condominio.dto; //* Package dedicato ai DTO

import lombok.Data; //* Lombok: genera automaticamente getter/setter/toString/equals/hashCode

import java.util.Map;

@Data //* Lombok: annotazione che crea i metodi standard (getter/setter ecc.)
public class UnitResponseDTO { //* Dichiarazione della classe DTO di output

    private Long id;              //* ID dell'unità immobiliare (chiave primaria generata dal DB)

    private String unitNumber;    //* Numero identificativo dell'unità (es. "Appartamento 1A")

    private String floor;         //* Piano dell'unità (es. "1", "T", "2A")

    private Integer sqM;          //* Superficie in metri quadri (se disponibile, può essere null)

    private Map<String, Object> metadata;  //* Metadati JSON gestiti come mappa chiave-valore, non come stringa

    private Long condominiumId;   //* ID del condominio a cui appartiene l'unità (FK verso tabella condominiums)

    private String condominiumName; //* Nome del condominio (campo utile per mostrare info senza serializzare l'intera entity)
}