package com.sigat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sigat.model.Contrato;
import com.sigat.model.EstadoRecibo;
import com.sigat.model.Recibo;

public interface ReciboRepository extends JpaRepository<Recibo, Long> {

    List<Recibo> findByContrato(Contrato contrato);

    List<Recibo> findByContratoAndEstadoRecibo(Contrato contrato, EstadoRecibo estadoRecibo);

    boolean existsByContratoAndMesAndAnio(Contrato contrato, Integer mes, Integer anio);
}
