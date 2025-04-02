package org.gdzdev.workshop.backend.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.domain.model.Servicing;
import org.gdzdev.workshop.backend.domain.port.out.ServicingRepositoryPort;
import org.gdzdev.workshop.backend.infrastructure.adapter.mapper.ServicingEntityMapper;
import org.gdzdev.workshop.backend.infrastructure.adapter.repos.ServicingJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ServicingJpaAdapter implements ServicingRepositoryPort {

    private final ServicingEntityMapper servicingMapper;
    private final ServicingJpaRepository jpaRepository;

    @Override
    public Optional<Servicing> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(servicingMapper::toModel);
    }
}
