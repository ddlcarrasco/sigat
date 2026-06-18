package com.sigat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sigat.model.Contrato;
import com.sigat.model.Titular;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    Optional<Contrato> findByNumeroContrato(String numeroContrato);

    List<Contrato> findByTitular(Titular titular);

    boolean existsByNumeroContrato(String numeroContrato);

    long countByEstadoContratoNombreIgnoreCase(String nombre);
}
