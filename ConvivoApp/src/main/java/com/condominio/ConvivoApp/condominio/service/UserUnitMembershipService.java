package com.condominio.ConvivoApp.condominio.service;

import com.condominio.ConvivoApp.condominio.entity.UserUnitMembership;
import com.condominio.ConvivoApp.condominio.entity.UserUnitMembershipId;
import com.condominio.ConvivoApp.condominio.repository.UserUnitMembershipRepository;
import com.condominio.ConvivoApp.user.entity.User;
import com.condominio.ConvivoApp.condominio.entity.Unit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserUnitMembershipService {

    private final UserUnitMembershipRepository repository;

    public UserUnitMembershipService(UserUnitMembershipRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public UserUnitMembership createMembership(User user, Unit unit, String role, boolean verified, String metadataJson) {
        UserUnitMembershipId id = new UserUnitMembershipId(user.getId(), unit.getId());

        UserUnitMembership membership = UserUnitMembership.builder()
                .id(id)
                .user(user)
                .unit(unit)
                .roleInUnit(role)
                .verified(verified)
                .metadata(metadataJson)
                .build();

        return repository.save(membership);
    }

    public UserUnitMembership getMembership(UserUnitMembershipId id) {
        return repository.findById(id).orElse(null);
    }
}
