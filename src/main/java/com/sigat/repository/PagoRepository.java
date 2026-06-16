package com.sigat.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sigat.model.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {

    // Para buscar un pago por su folio (comprobante)
    Optional<Pago> findByFolio(String folio);

    // Para verificar que el folio no este duplicado
    boolean existsByFolio(String folio);
}
