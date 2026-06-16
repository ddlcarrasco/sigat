package com.sigat.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sigat.model.Contrato;
import com.sigat.model.HistorialEstadoContrato;

public interface HistorialEstadoContratoRepository extends JpaRepository<HistorialEstadoContrato, Long> {

    // Para ver la cronologia completa de cambios de estado de un contrato
    List<HistorialEstadoContrato> findByContratoOrderByFechaAsc(Contrato contrato);
}
