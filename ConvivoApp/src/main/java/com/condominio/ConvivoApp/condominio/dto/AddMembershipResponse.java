/*******************************************************
 ** AddMembershipResponse.java
 ** -----------------------------------------------------
 ** DTO di OUTPUT per restituire al client i dati di una
 ** membership (associazione utente ↔ unità).
 ** Usato da MembershipMapper.toDto.
 *******************************************************/

package com.condominio.ConvivoApp.condominio.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class AddMembershipResponse {

    private Long id;                        //* Identificativo della membership
    private Long userId;                    //* ID dell'utente associato
    private Long unitId;                    //* ID dell'unità associata
    private String role;                    //* Ruolo dell'utente nell'unità (es. "owner", "tenant")
    private Map<String,Object> metadata;    //* Metadati JSON come mappa
}