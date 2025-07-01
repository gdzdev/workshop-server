package org.gdzdev.workshop.backend.application.usecase;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.application.dto.user.JwtDto;
import org.gdzdev.workshop.backend.application.dto.user.UserRequest;
import org.gdzdev.workshop.backend.application.dto.user.UserResponse;
import org.gdzdev.workshop.backend.application.usecase.shared.JwtServiceImpl;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.UserEntity;
import org.gdzdev.workshop.backend.infrastructure.adapter.mapper.UserEntityMapper;
import org.gdzdev.workshop.backend.infrastructure.adapter.repos.UserJpaRepository;
import org.gdzdev.workshop.backend.infrastructure.rest.exceptions.InvalidJwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements UserDetailsService {
    private final UserJpaRepository _repository;
    private final UserEntityMapper _mapper;
    private final JwtServiceImpl _jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = this._repository.findByLogin(username);

        if (user == null) throw new RuntimeException("User not found");

        return user;
    }

    public JwtDto signing(UserRequest data, Authentication authUser) {
        var accessToken = this._jwtService.generateAccessToken((UserEntity) authUser.getPrincipal());

        return new JwtDto(accessToken);
    }

    public UserResponse signUp(UserRequest data) throws InvalidJwtException {
        if (this._repository.findByLogin(data.getName()) != null) {
            throw new InvalidJwtException("Username already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());

        UserEntity newUser = new UserEntity();

        newUser.setLogin(data.getName());
        newUser.setPassword(encryptedPassword);
        newUser.setRole(data.getRole());
        newUser.setCode(data.getCode());
        newUser.setPhoneNumber(data.getNumber());

        UserEntity entity = this._repository.save(newUser);

        return this._mapper.toResponse(entity);
    }
}
