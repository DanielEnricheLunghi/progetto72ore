/***
 ** UnitRepository.java
 ** -------------------
 ** Questo file rappresenta il repository JPA per l'entità "Unit".
 ** Serve per eseguire operazioni CRUD e query personalizzate sulla tabella "units".
 ***/
package com.condominio.ConvivoApp.condominio.repository; //*Package repository del modulo condominio

import com.condominio.ConvivoApp.condominio.entity.Unit;        //* Import dell'entità Unit
import com.condominio.ConvivoApp.condominio.entity.Condominium; //* Import dell'entità Condominium
import org.springframework.data.jpa.repository.JpaRepository;   //* Import di JpaRepository
import java.util.List;                                          //* Import di List per risultati multipli

/***
 ** Interfaccia che estende JpaRepository:
 ** - Entità: Unit
 ** - PK: Long
 ***/
public interface UnitRepository extends JpaRepository<Unit, Long> {

    /***
     ** Metodo derivato da Spring Data JPA.
     ** Restituisce tutte le unità appartenenti a un condominio specifico.
     ** SELECT * FROM units WHERE condominium_id = ?;
     ***/
    List<Unit> findByCondominium(Condominium condominium);
}