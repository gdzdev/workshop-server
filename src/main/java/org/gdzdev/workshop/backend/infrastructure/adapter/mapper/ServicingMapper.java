package org.gdzdev.workshop.backend.infrastructure.adapter.mapper;

import org.gdzdev.workshop.backend.domain.model.Servicing;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.ServicingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface ServicingMapper {

    Servicing toModel(ServicingEntity servicingEntity);
    ServicingEntity toEntity(Servicing servicing);
}
