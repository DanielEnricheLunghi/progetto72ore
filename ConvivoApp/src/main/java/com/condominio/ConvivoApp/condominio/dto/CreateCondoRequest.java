/***
 ** CreateCondoRequest.java
 ** -----------------------
 ** Questo file rappresenta il DTO per la creazione di un condominio.
 ** Serve come oggetto di input per il service e il controller.
 ***/
package com.condominio.ConvivoApp.condominio.dto; //* Package dto del modulo condominio

import jakarta.validation.constraints.*; //* Import per validazioni
import lombok.Data;                      //* Lombok: genera getter/setter automaticamente

import java.util.Map;

@Data //* Lombok: genera getter, setter, equals, hashCode, toString
public class CreateCondoRequest {

    @NotBlank @Size(max = 255) //* Nome obbligatorio, max 255 caratteri
    private String name;

    @Size(max = 5000)          //* Indirizzo facoltativo, max 5000 caratteri
    private String address;

    @Size(max = 100)           //* Città facoltativa, max 100 caratteri
    private String city;

    @Size(max = 20)            //* CAP facoltativo, max 20 caratteri
    private String postcode;

    private Map<String, Object> metadata; //* Metadati JSON come mappa

}