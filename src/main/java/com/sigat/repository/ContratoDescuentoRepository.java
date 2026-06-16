package com.sigat.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sigat.model.Contrato;
import com.sigat.model.ContratoDescuento;

public interface ContratoDescuentoRepository extends JpaRepository<ContratoDescuento, Long> {

    // Para obtener todos los descuentos aplicados a un contrato
    List<ContratoDescuento> findByContrato(Contrato contrato);
}
