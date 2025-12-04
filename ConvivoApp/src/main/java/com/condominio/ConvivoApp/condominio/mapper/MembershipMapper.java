/*******************************************************
 ** MembershipMapper.java
 ** -----------------------------------------------------
 ** Questo file rappresenta un mapper DTO ↔ Entity.
 ** Serve per convertire un oggetto di input (DTO) come
 ** `AddMembershipRequest` in un oggetto di dominio (Entity)
 ** `UserUnitMembership`, pronto per essere salvato nel database.
 *
 ** Obiettivo:
 ** - Centralizzare la logica di conversione
 ** - Rendere i service più puliti e leggibili
 ** - Evitare duplicazioni di codice
 *******************************************************/

package com.condominio.ConvivoApp.condominio.mapper; //* Package: cartella mapper del modulo condominio

import com.condominio.ConvivoApp.condominio.dto.AddMembershipRequest; //* Import del DTO di input
import com.condominio.ConvivoApp.condominio.entity.UserUnitMembership; //* Import dell'Entity di dominio
import com.condominio.ConvivoApp.condominio.entity.UserUnitMembershipId; //* Import della PK composta

import java.util.UUID;

public class MembershipMapper { //* Classe pubblica che contiene i metodi di mapping

    /***
     ** Metodo statico che converte un DTO `AddMembershipRequest`
     ** in un Entity `UserUnitMembership`.
     *
     ** @param req DTO ricevuto dal controller (input JSON)
     ** @return Entity `UserUnitMembership` pronto per il salvataggio
     ***/
    public static UserUnitMembership toEntity(AddMembershipRequest req) {
        UserUnitMembership membership = new UserUnitMembership(); //* Istanzia un nuovo oggetto Membership

        UUID userId = UUID.fromString(req.getUserId()); // ✅ conversione esplicita
        membership.setId(new UserUnitMembershipId(userId, req.getUnitId()));



        membership.setRoleInUnit(req.getRoleInUnit()); //* Copia il ruolo dell'utente nell'unità
        membership.setVerified(req.isVerified());      //* Copia lo stato di verifica

        return membership; //* Restituisce l'Entity costruito
    }
}