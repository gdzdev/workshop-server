package org.gdzdev.workshop.backend.infrastructure.adapter.mapper;

import org.gdzdev.workshop.backend.application.dto.user.UserResponse;
import org.gdzdev.workshop.backend.domain.model.User;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {
    @Mapping(source = "login", target = "name")
    User toModel(UserEntity entity);

    User toModel(UserResponse response);

    @Mapping(source = "name", target = "login")
    UserEntity toEntity(User model);

    @Mapping(source = "name", target = "login")
    UserEntity toEntity(UserResponse response);

    UserResponse toResponse(User model);

    @Mapping(source = "login", target = "name")
    UserResponse toResponse(UserEntity entity);
}