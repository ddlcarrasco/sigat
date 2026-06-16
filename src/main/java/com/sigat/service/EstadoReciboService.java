package com.sigat.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sigat.model.EstadoRecibo;
import com.sigat.repository.EstadoReciboRepository;

@Service
public class EstadoReciboService {

    private final EstadoReciboRepository estadoReciboRepository;

    public EstadoReciboService(EstadoReciboRepository estadoReciboRepository) {
        this.estadoReciboRepository = estadoReciboRepository;
    }

    public List<EstadoRecibo> getAll() {
        return estadoReciboRepository.findAll();
    }

    public EstadoRecibo getById(Integer id) {
        return estadoReciboRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado de recibo no encontrado: " + id));
    }
}
