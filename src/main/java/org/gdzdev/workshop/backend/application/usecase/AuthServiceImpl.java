package org.gdzdev.workshop.backend.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdzdev.workshop.backend.application.dto.user.*;
import org.gdzdev.workshop.backend.application.usecase.shared.JwtServiceImpl;
import org.gdzdev.workshop.backend.domain.enums.UserRole;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.UserEntity;
import org.gdzdev.workshop.backend.infrastructure.adapter.mapper.UserEntityMapper;
import org.gdzdev.workshop.backend.infrastructure.adapter.repos.UserJpaRepository;
import org.gdzdev.workshop.backend.infrastructure.rest.exceptions.InvalidJwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
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


    public JwtDto signing(SigninDto data, Authentication authUser) {
        var accessToken = this._jwtService.generateAccessToken((UserEntity) authUser.getPrincipal());

        return new JwtDto(accessToken);
    }

    public UserResponse signUp(SignupDto data) throws InvalidJwtException {
        if (this._repository.findByLogin(data.getName()) != null) {
            throw new InvalidJwtException("Username already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());

        UserEntity newUser = new UserEntity();

        newUser.setLogin(data.getName());
        newUser.setLastName(data.getLastName());
        newUser.setPassword(encryptedPassword);
        newUser.setCode(data.getCode());
        newUser.setRole(UserRole.USER);
        newUser.setEmail(data.getEmail());

        UserEntity entity = this._repository.save(newUser);

        return this._mapper.toResponse(entity);
    }

    public boolean validateToken(RefreshTokenDto token) {
        String tokenValidated = this._jwtService.validateToken(token.getToken());

        return tokenValidated != null;
    }
}
