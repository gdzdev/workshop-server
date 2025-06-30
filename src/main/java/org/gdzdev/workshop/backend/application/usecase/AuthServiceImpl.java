package org.gdzdev.workshop.backend.application.usecase;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.application.dto.user.UserRequest;
//import org.gdzdev.workshop.backend.domain.port.input.AuthService;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.UserEntity;
import org.gdzdev.workshop.backend.infrastructure.adapter.repos.UserJpaRepository;
import org.gdzdev.workshop.backend.infrastructure.rest.exceptions.InvalidJwtException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements UserDetailsService {
    private final UserJpaRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = repository.findByLogin(username);

        if (user == null) throw new RuntimeException("User not found");

        return user;
    }

    public UserDetails signUp(UserRequest data) throws InvalidJwtException {
        if (repository.findByLogin(data.getName()) != null) {
            throw new InvalidJwtException("Username already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
//        User newUser = new User(data.getName(), encryptedPassword, data.getRole());

        UserEntity newUser = new UserEntity();

        newUser.setLogin(data.getName());
        newUser.setPassword(encryptedPassword);
        newUser.setRole(data.getRole());
        newUser.setCode(data.getCode());
        newUser.setPhoneNumber(data.getNumber());

        return this.repository.save(newUser);
    }
}
