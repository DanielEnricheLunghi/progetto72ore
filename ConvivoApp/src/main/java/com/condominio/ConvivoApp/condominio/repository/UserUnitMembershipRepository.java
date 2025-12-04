
package com.condominio.ConvivoApp.condominio.repository;


import com.condominio.ConvivoApp.condominio.entity.UserUnitMembership;
import com.condominio.ConvivoApp.condominio.entity.UserUnitMembershipId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserUnitMembershipRepository extends JpaRepository<UserUnitMembership, UserUnitMembershipId> {

    //* Trova tutte le membership per un'unità
    List<UserUnitMembership> findById_UnitId(Long unitId);

    //* Trova tutte le membership per un utente
    List<UserUnitMembership> findById_UserId(String userId);
}
