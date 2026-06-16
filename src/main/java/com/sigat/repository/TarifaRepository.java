package com.sigat.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sigat.model.Categoria;
import com.sigat.model.Sector;
import com.sigat.model.Tarifa;

public interface TarifaRepository extends JpaRepository<Tarifa, Integer> {

    List<Tarifa> findByActiva(Integer activa);

    // Para obtener la tarifa vigente de un sector+categoria al generar un recibo
    Optional<Tarifa> findBySectorAndCategoriaAndActiva(Sector sector, Categoria categoria, Integer activa);
}
