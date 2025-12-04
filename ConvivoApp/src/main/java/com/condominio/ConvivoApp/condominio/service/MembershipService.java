package com.condominio.ConvivoApp.condominio.service;

import com.condominio.ConvivoApp.condominio.dto.AddMembershipRequest;
import com.condominio.ConvivoApp.condominio.entity.UserUnitMembership;
import com.condominio.ConvivoApp.condominio.entity.UserUnitMembershipId;
import com.condominio.ConvivoApp.condominio.repository.UserUnitMembershipRepository;
import com.condominio.ConvivoApp.condominio.mapper.MembershipMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MembershipService {

    private final UserUnitMembershipRepository membershipRepo;

    /**
     * Crea una nuova membership.
     * @param req DTO della richiesta
     * @return UserUnitMembership salvata nel DB
     */
    public UserUnitMembership create(@Valid AddMembershipRequest req) {
        UserUnitMembership membership = MembershipMapper.toEntity(req);
        return membershipRepo.save(membership);
    }

    /**
     * Restituisce tutte le membership.
     * @return lista di membership
     */
    @Transactional(readOnly = true)
    public List<UserUnitMembership> list() {
        return membershipRepo.findAll();
    }

    /**
     * Recupera una membership per PK composta.
     * @param userId UUID dell'utente
     * @param unitId Long ID dell'unità
     * @return UserUnitMembership
     */
    @Transactional(readOnly = true)
    public UserUnitMembership getOrThrow(UUID userId, Long unitId) {
        UserUnitMembershipId id = new UserUnitMembershipId(userId, unitId);
        return membershipRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Membership not found"));
    }

    /**
     * Lista membership filtrata per unità.
     * @param unitId ID dell'unità
     * @return lista membership
     */
    @Transactional(readOnly = true)
    public List<UserUnitMembership> listByUnit(Long unitId) {
        return membershipRepo.findById_UnitId(unitId);
    }

    /**
     * Lista membership filtrata per utente.
     * @param userId UUID dell'utente come stringa
     * @return lista membership
     */
    @Transactional(readOnly = true)
    public List<UserUnitMembership> listByUser(UUID userId) {
        return membershipRepo.findById_UserId(userId.toString());
    }
}