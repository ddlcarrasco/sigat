package com.sigat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sigat.model.EstadoTramite;

public interface EstadoTramiteRepository extends JpaRepository<EstadoTramite, Long> {
}
