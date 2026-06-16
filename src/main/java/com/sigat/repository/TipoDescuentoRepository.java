package com.sigat.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sigat.model.TipoDescuento;

public interface TipoDescuentoRepository extends JpaRepository<TipoDescuento, Long> {

    // Para mostrar solo los descuentos vigentes al asignar a un contrato
    List<TipoDescuento> findByActivo(Integer activo);
}
