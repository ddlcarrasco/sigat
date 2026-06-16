package com.sigat.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sigat.model.Sector;

public interface SectorRepository extends JpaRepository<Sector, Long> {

    // Para listar solo los sectores activos en el frontend
    List<Sector> findByActivo(Integer activo);
}
