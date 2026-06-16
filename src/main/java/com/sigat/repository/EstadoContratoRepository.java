package com.sigat.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sigat.model.EstadoContrato;

public interface EstadoContratoRepository extends JpaRepository<EstadoContrato, Long> {

    Optional<EstadoContrato> findByNombre(String nombre);
}
