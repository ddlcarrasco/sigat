package com.sigat.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sigat.model.TipoPago;
import com.sigat.repository.TipoPagoRepository;

@Service
public class TipoPagoService {

    private final TipoPagoRepository tipoPagoRepository;

    public TipoPagoService(TipoPagoRepository tipoPagoRepository) {
        this.tipoPagoRepository = tipoPagoRepository;
    }

    public List<TipoPago> getAll() {
        return tipoPagoRepository.findAll();
    }

    public TipoPago getById(Long id) {
        return tipoPagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de pago no encontrado: " + id));
    }
}
