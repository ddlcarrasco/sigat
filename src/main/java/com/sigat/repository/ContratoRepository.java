package com.sigat.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sigat.model.Contrato;
import com.sigat.model.Titular;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    // Para buscar un contrato por su numero unico (ej: TLX-2024-0001)
    Optional<Contrato> findByNumeroContrato(String numeroContrato);

    // Para ver todos los contratos de un titular
    List<Contrato> findByTitular(Titular titular);

    // Para verificar que el numero de contrato no este duplicado
    boolean existsByNumeroContrato(String numeroContrato);
}
