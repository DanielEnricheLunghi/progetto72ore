/***
 ** Unit.java
 ** ---------
 ** Questo file rappresenta l'entità JPA "Unit".
 ** Serve per mappare la tabella "units" in MySQL.
 ** Ogni unità immobiliare appartiene a un condominio e può avere più membership utente-unità.
 ***/
package com.condominio.ConvivoApp.condominio.entity; //* Package del modulo condominio (cartella entity)


import jakarta.persistence.*; //* Import annotazioni JPA
import lombok.*;              //* Import annotazioni Lombok


import java.util.Set;         //* Import di Set per la collezione di membership

@Entity                                          //* Indica che questa classe è una entity JPA
@Table(name = "units",                           //* Specifica il nome della tabella
        indexes = {                              //* Definisce indici per velocizzare le query
                @Index(name = "idx_units_condo",        //* Nome dell'indice
                        columnList = "condominium_id")  //* Colonne incluse nell'indice (FK verso condominio)
        })
@Data                                            //* Lombok: getter/setter/equals/hashCode/toString
@Builder                                         //* Lombok: builder pattern
@NoArgsConstructor                               //* Lombok: costruttore vuoto
@AllArgsConstructor                              //* Lombok: costruttore con argomenti
public class Unit {                              //* Dichiarazione della classe entity

    @Id                                          //* Chiave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //* AUTO_INCREMENT in MySQL
    private Long id;                             //* ID dell'unità immobiliare

    @ManyToOne(optional = false)                 //* Relazione N:1 (ogni unità appartiene a un condominio)
    @JoinColumn(name = "condominium_id",         //* Nome della colonna FK
            nullable = false)                //*FK non può essere null
    private Condominium condominium;             //* Riferimento al condominio di appartenenza

    @Column(length = 50)                         //* Colonna con max 50 caratteri
    private String unitNumber;                   //* Identificativo interno dell'unità (es. "A12")

    @Column(length = 50)                         //* Colonna con max 50 caratteri
    private String floor;                        //* Piano (es. "2", "T")

    @Column(name = "sq_m")                       //* Nome colonna coerente con DDL (sq_m)
    private Integer sqM;                         //* Superficie in metri quadri (facoltativa)

    @Column(name = "metadata", columnDefinition = "TEXT") //* Mappa la proprietà 'metadata' alla colonna DB; usa tipo TEXT per contenere JSON di qualsiasi dimensione
    private String metadata;                              //* Campo effettivo salvato nel DB come stringa JSON (non Map)


    @OneToMany(mappedBy = "unit",                //* Relazione 1:N verso UserUnitMembership (FK in membership)
            cascade = CascadeType.ALL,        //* Propaga persistenza su membership collegate
            orphanRemoval = false)            //* Non rimuove automaticamente membership scollegate
    private Set<UserUnitMembership> memberships; //* Collezione di membership utente-unità
}