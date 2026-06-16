package com.sigat.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sigat.model.EstadoTramite;
import com.sigat.repository.EstadoTramiteRepository;

@Service
public class EstadoTramiteService {

    private final EstadoTramiteRepository estadoTramiteRepository;

    public EstadoTramiteService(EstadoTramiteRepository estadoTramiteRepository) {
        this.estadoTramiteRepository = estadoTramiteRepository;
    }

    public List<EstadoTramite> getAll() {
        return estadoTramiteRepository.findAll();
    }

    public EstadoTramite getById(Long id) {
        return estadoTramiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado de tramite no encontrado: " + id));
    }
}
