package com.sigat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sigat.model.EstadoRecibo;

public interface EstadoReciboRepository extends JpaRepository<EstadoRecibo, Integer> {
}
