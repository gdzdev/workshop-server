package org.gdzdev.workshop.backend.infrastructure.adapter.mapper;

import org.gdzdev.workshop.backend.domain.model.Servicing;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.ServicingEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicingEntityMapper {

    Servicing toModel(ServicingEntity servicingEntity);
    ServicingEntity toEntity(Servicing servicing);
}
