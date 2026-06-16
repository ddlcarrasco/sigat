package com.sigat.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sigat.model.EstadoContrato;
import com.sigat.repository.EstadoContratoRepository;

@Service
public class EstadoContratoService {

    private final EstadoContratoRepository estadoContratoRepository;

    public EstadoContratoService(EstadoContratoRepository estadoContratoRepository) {
        this.estadoContratoRepository = estadoContratoRepository;
    }

    public List<EstadoContrato> getAll() {
        return estadoContratoRepository.findAll();
    }

    public EstadoContrato getById(Long id) {
        return estadoContratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado de contrato no encontrado: " + id));
    }
}
