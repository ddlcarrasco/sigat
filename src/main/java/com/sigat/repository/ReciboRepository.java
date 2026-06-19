package com.sigat.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sigat.model.Contrato;
import com.sigat.model.EstadoRecibo;
import com.sigat.model.Recibo;

public interface ReciboRepository extends JpaRepository<Recibo, Long> {

    List<Recibo> findByContrato(Contrato contrato);

    List<Recibo> findByContratoAndEstadoRecibo(Contrato contrato, EstadoRecibo estadoRecibo);

    boolean existsByContratoAndMesAndAnio(Contrato contrato, Integer mes, Integer anio);

    List<Recibo> findByPagoIsNull();

    long countByPagoIsNull();

    List<Recibo> findByPagoIsNotNullAndPagoFechaPagoBetween(LocalDateTime desde, LocalDateTime hasta);

    List<Recibo> findByPagoIsNotNull();

    List<Recibo> findByPago_Idpago(Long idpago);
}
