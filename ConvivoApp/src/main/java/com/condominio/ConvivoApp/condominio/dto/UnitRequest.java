/***
 ** UnitRequest.java
 ** ----------------
 ** Questa classe rappresenta un DTO (Data Transfer Object).
 ** Serve per ricevere i dati dal client quando si crea una nuova "Unit".
 ** A differenza dell'entità JPA, qui non c'è la relazione diretta con Condominium,
 ** ma solo l'ID del condominio, così il JSON può essere semplice (con condominiumId).
 ** Il controller userà questa classe per mappare la richiesta e poi
 ** convertirla in un'entità "Unit" collegata al condominio corretto.
 ***/

package com.condominio.ConvivoApp.condominio.dto; // Package dedicato ai DTO

import lombok.Data; //* Lombok: genera automaticamente getter/setter/toString/equals/hashCode

@Data //* Lombok: annotazione che crea i metodi standard (getter/setter ecc.)
public class UnitRequest { //* Dichiarazione della classe DTO

    private String unitNumber; //* Numero identificativo dell'unità (es. "Appartamento 1A")

    private String floor;      //* Piano dell'unità (es. "1", "T", "2A")

    private Long condominiumId; //* ID del condominio a cui appartiene l'unità
}