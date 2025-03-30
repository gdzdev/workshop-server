package org.gdzdev.workshop.backend.domain.port.out;

import org.gdzdev.workshop.backend.domain.model.Servicing;

import java.util.Optional;

public interface ServicingRepositoryPort {

    Optional<Servicing> findById(Long id);
}
