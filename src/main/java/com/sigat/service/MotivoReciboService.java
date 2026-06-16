package com.sigat.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sigat.model.MotivoRecibo;
import com.sigat.repository.MotivoReciboRepository;

@Service
public class MotivoReciboService {

    private final MotivoReciboRepository motivoReciboRepository;

    public MotivoReciboService(MotivoReciboRepository motivoReciboRepository) {
        this.motivoReciboRepository = motivoReciboRepository;
    }

    public List<MotivoRecibo> getAll() {
        return motivoReciboRepository.findAll();
    }

    public MotivoRecibo getById(Integer id) {
        return motivoReciboRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Motivo de recibo no encontrado: " + id));
    }
}
