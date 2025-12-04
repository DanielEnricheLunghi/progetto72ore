/***
 ** CondominiumRepository.java
 ** --------------------------
 ** Questo file rappresenta il repository JPA per l'entità "Condominium".
 ** Serve per eseguire operazioni CRUD (Create, Read, Update, Delete) e query personalizzate
 ** sulla tabella "condominiums" in MySQL.
 ***/
package com.condominio.ConvivoApp.condominio.repository; //* Package repository del modulo condominio

import com.condominio.ConvivoApp.condominio.entity.Condominium; //*Import dell'entità Condominium
import org.springframework.data.jpa.repository.JpaRepository;   //*Import di JpaRepository per CRUD automatico

/***
 ** Interfaccia che estende JpaRepository:
 ** - Primo parametro: tipo dell'entità (Condominium).
 ** - Secondo parametro: tipo della chiave primaria (Long).
 ***/
public interface CondominiumRepository extends JpaRepository<Condominium, Long> {

    /***
     ** Metodo derivato da Spring Data JPA.
     ** Genera automaticamente una query per verificare se esiste un condominio con un certo nome.
     ** SELECT COUNT(*) FROM condominiums WHERE name = ?;
     ***/
    boolean existsByName(String name);
}