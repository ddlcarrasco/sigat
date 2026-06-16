package com.sigat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sigat.model.Contrato;
import com.sigat.model.EstadoTramite;
import com.sigat.model.Tramite;

public interface TramiteRepository extends JpaRepository<Tramite, Long> {

    List<Tramite> findByContrato(Contrato contrato);

    List<Tramite> findByEstadoTramite(EstadoTramite estadoTramite);
}
