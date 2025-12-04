/***
 ** Condominium.java
 ** ----------------
 ** Questo file rappresenta l'entità JPA "Condominium".
 ** Serve per mappare la tabella "condominiums" in MySQL.
 ** Contiene i dati anagrafici del condominio e la relazione con le unità immobiliari.
 ***/
package com.condominio.ConvivoApp.condominio.entity; //* Package del modulo condominio (cartella entity)


import jakarta.persistence.*; //* Import delle annotazioni JPA per mappare la classe alla tabella
import lombok.*;              //* Import di Lombok per generare boilerplate (getter/setter/builder)




@Entity                           //* Indica che questa classe è una entity JPA
@Table(name = "condominiums")     //* Specifica il nome della tabella in MySQL
@Data                             //* Lombok: genera getter, setter, equals/hashCode, toString
@Builder                          //* Lombok: abilita il pattern builder
@NoArgsConstructor                //* Lombok: costruttore senza argomenti
@AllArgsConstructor               //* Lombok: costruttore con tutti gli argomenti
public class Condominium {        //* Dichiarazione della classe entity

    @Id                          //* Indica il campo come chiave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //* Usa AUTO_INCREMENT di MySQL
    private Long id;                         //* Identificativo univoco del condominio

    @Column(nullable = false, length = 255)  //* Colonna obbligatoria con lunghezza massima 255
    private String name;                     //* Nome del condominio

    @Column(columnDefinition = "TEXT")       //* Usa tipo TEXT per indirizzi lunghi
    private String address;                  //* Indirizzo del condominio

    @Column(length = 100)                    //* Colonna con massimo 100 caratteri
    private String city;                     //* Città del condominio

    @Column(length = 20)                     //* Colonna con massimo 20 caratteri
    private String postcode;                 //* CAP del condominio


    @Column(name = "metadata", columnDefinition = "TEXT") //* Mappa la proprietà 'metadata' alla colonna DB; usa tipo TEXT per contenere JSON di qualsiasi dimensione
    private String metadata;                              //* Campo effettivo salvato nel DB come stringa JSON (non Map)
}