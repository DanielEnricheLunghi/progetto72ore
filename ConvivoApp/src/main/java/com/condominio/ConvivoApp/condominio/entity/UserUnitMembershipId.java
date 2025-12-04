/***
 ** UserUnitMembershipId.java
 ** -------------------------
 ** Questo file rappresenta la chiave primaria composta per la tabella "user_unit_membership".
 ** La PK è formata da (userId, unitId).
 ** Serve per garantire l'unicità della relazione utente ↔ unità e supportare il mapping JPA con @EmbeddedId.
 ***/
package com.condominio.ConvivoApp.condominio.entity; //* Package del modulo condominio (cartella entity)

import jakarta.persistence.*; //* Import per @Embeddable e @Column
import lombok.*;              //* Import Lombok per boilerplate
import java.io.Serializable;  //* Import dell'interfaccia marker Serializable (necessaria per PK composta)
import java.util.UUID;

@Embeddable                               //* Indica che questa classe può essere incorporata come PK in un'entity
@Data                                      //* Lombok: getter/setter/equals/hashCode/toString
@NoArgsConstructor                         //* Lombok: costruttore vuoto
@AllArgsConstructor                        //* Lombok: costruttore con argomenti
public class UserUnitMembershipId implements Serializable { //* Classe PK composta che implementa Serializable

    @Column(name = "user_id",               //* Nome della colonna per l'UUID dell'utente
            columnDefinition = "BINARY(16)")  //* Mappa l'UUID string su CHAR(36) (standard con trattini)
    private UUID userId;                  //* Identificativo utente (UUID in stringa)

    @Column(name = "unit_id")               //* Nome della colonna per l'ID dell'unità
    private Long unitId;                    //* Identificativo dell'unità (FK verso Unit.id)
}