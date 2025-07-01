package org.gdzdev.workshop.backend.infrastructure.adapter.repos;

import org.gdzdev.workshop.backend.domain.enums.UserRole;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByCode(String code);
    UserDetails findByLogin(String login);

    long deleteByEmail(String email);
    long deleteByCode(String code);
}