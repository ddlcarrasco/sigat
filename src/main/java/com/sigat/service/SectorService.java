package com.sigat.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sigat.model.Sector;
import com.sigat.repository.SectorRepository;

@Service
public class SectorService {

    private final SectorRepository sectorRepository;

    public SectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    public List<Sector> getAll() {
        return sectorRepository.findAll();
    }

    public List<Sector> getAllActivos() {
        return sectorRepository.findByActivo(1);
    }

    public Sector getById(Long id) {
        return sectorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sector no encontrado: " + id));
    }

    public Sector save(Sector sector) {
        return sectorRepository.save(sector);
    }
}
