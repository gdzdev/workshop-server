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
    @Mapping(source = "phoneNumber", target = "number")
    User toModel(UserEntity entity);

    User toModel(UserResponse response);

    @Mapping(source = "name", target = "login")
    @Mapping(source = "number", target = "phoneNumber")
    UserEntity toEntity(User model);

    @Mapping(source = "name", target = "login")
    @Mapping(source = "number", target = "phoneNumber")
    UserEntity toEntity(UserResponse response);

    UserResponse toResponse(User model);

    @Mapping(source = "login", target = "name")
    @Mapping(source = "phoneNumber", target = "number")
    UserResponse toResponse(UserEntity entity);
}