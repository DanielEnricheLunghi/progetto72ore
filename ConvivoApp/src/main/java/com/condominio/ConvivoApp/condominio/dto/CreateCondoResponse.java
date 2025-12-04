/*******************************************************
 ** CreateCondoResponse.java
 ** -----------------------------------------------------
 ** DTO di OUTPUT per restituire al client i dati di un
 ** condominio. Usato da CondominiumMapper.toDto.
 **
 ** Obiettivi:
 ** - Esporre solo i campi utili al client
 ** - Restituire metadata come Map (JSON → Map)
 ** - Evitare di esporre direttamente l'entità JPA
 *******************************************************/

package com.condominio.ConvivoApp.condominio.dto; // Package DTO del modulo condominio

import lombok.Builder;  //* Lombok: abilita il pattern builder
import lombok.Data;     //* Lombok: genera getter/setter/toString/equals/hashCode
import java.util.Map;

@Data        //* Genera automaticamente getter/setter e metodi utili
@Builder     //* Permette di costruire l’oggetto con CreateCondoResponse.builder()
public class CreateCondoResponse { //* Dichiarazione della classe DTO di output

    private Long id;                        //* Identificativo del condominio (PK dal DB)
    private String name;                    //* Nome del condominio
    private String address;                 //* Indirizzo
    private String city;                    //* Città
    private String postcode;                //* CAP
    private Map<String,Object> metadata;    //* Metadati JSON restituiti come mappa
}