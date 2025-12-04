/*******************************************************
 ** CondominiumMapper.java
 ** -----------------------------------------------------
 ** Mapper DTO ↔ Entity per la classe Condominium.
 ** Gestisce sia la conversione da DTO di input a Entity
 ** (toEntity) sia la conversione inversa da Entity a DTO
 ** di output (toDto).
 *******************************************************/

package com.condominio.ConvivoApp.condominio.mapper; //* Package: cartella mapper del modulo condominio

//* Import del DTO di input (richiesta creazione condominio)
import com.condominio.ConvivoApp.condominio.dto.CreateCondoRequest;

//* Import del DTO di output (risposta al client)
import com.condominio.ConvivoApp.condominio.dto.CreateCondoResponse;

//* Import dell'Entity JPA di dominio (tabella condominium)
import com.condominio.ConvivoApp.condominio.entity.Condominium;

//* Import della utility per conversione Map ↔ JSON String
import com.condominio.ConvivoApp.condominio.util.MetadataUtils;

public class CondominiumMapper { //* Dichiarazione della classe mapper

    /***
     ** Metodo statico che converte un DTO CreateCondoRequest
     ** in un Entity Condominium.
     **
     ** @param req DTO ricevuto dal client (input JSON)
     ** @return Entity Condominium pronto per il salvataggio
     ***/
    public static Condominium toEntity(CreateCondoRequest req) {
        return Condominium.builder()                          //* Usa il builder di Lombok per creare l'Entity
                .name(req.getName())                          //* Copia il campo "name" dal DTO all'Entity
                .address(req.getAddress())                    //* Copia il campo "address"
                .city(req.getCity())                          //* Copia il campo "city"
                .postcode(req.getPostcode())                  //* Copia il campo "postcode"
                .metadata(MetadataUtils.serialize(req.getMetadata())) //* Converte la Map in JSON string (Map → String)
                .build();                                     //* Costruisce l'Entity completo
    }

    /***
     ** Metodo statico che converte un Entity Condominium
     ** in un DTO CreateCondoResponse.
     **
     ** @param entity Entity letto/salvato dal DB
     ** @return DTO CreateCondoResponse da restituire al client
     ***/
    public static CreateCondoResponse toDto(Condominium entity) {
        return CreateCondoResponse.builder()                  //* Usa il builder di Lombok per creare il DTO
                .id(entity.getId())                           //* Copia l'ID dal DB
                .name(entity.getName())                       //* Copia il campo "name"
                .address(entity.getAddress())                 //* Copia il campo "address"
                .city(entity.getCity())                       //* Copia il campo "city"
                .postcode(entity.getPostcode())               //* Copia il campo "postcode"
                .metadata(MetadataUtils.parse(entity.getMetadata())) //* Converte la String JSON in Map (String → Map)
                .build();                                     //* Costruisce il DTO completo
    }
}