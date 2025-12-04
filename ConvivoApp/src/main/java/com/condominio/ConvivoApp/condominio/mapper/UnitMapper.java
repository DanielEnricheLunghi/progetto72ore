/*******************************************************
 ** UnitMapper.java
 ** -----------------------------------------------------
 ** Questo file rappresenta un mapper DTO ↔ Entity.
 ** Serve per convertire un oggetto di input (DTO) come
 ** `CreateUnitRequest` in un oggetto di dominio (Entity)
 ** `Unit`, pronto per essere salvato nel database.
 *
 ** Obiettivo:
 ** - Centralizzare la logica di conversione
 ** - Rendere i service più puliti e leggibili
 ** - Evitare duplicazioni di codice
 *******************************************************/

package com.condominio.ConvivoApp.condominio.mapper; //* Package: cartella mapper del modulo condominio

import com.condominio.ConvivoApp.condominio.dto.CreateUnitRequest; //* Import del DTO di input
import com.condominio.ConvivoApp.condominio.entity.Unit;           //* Import dell'Entity di dominio
import com.condominio.ConvivoApp.condominio.util.MetadataUtils;

public class UnitMapper { //* Classe pubblica che contiene i metodi di mapping

    /***
     ** Metodo statico che converte un DTO `CreateUnitRequest`
     ** in un Entity `Unit`.
     *
     ** @param req DTO ricevuto dal controller (input JSON)
     ** @return Entity `Unit` pronto per il salvataggio
     ***/
    public static Unit toEntity(CreateUnitRequest req) {
        Unit unit = new Unit(); //* Istanzia un nuovo oggetto Unit

        unit.setUnitNumber(req.getUnitNumber()); //* Copia il campo "unitNumber"
        unit.setFloor(req.getFloor());           //* Copia il campo "floor"
        unit.setSqM(req.getSqM());               //* Copia il campo "sqM"
        unit.setMetadata(MetadataUtils.serialize(req.getMetadata())); //* FIX: Map → String

        return unit; //* Restituisce l'Entity costruito
    }
}