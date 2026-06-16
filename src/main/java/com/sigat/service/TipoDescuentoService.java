package com.sigat.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sigat.model.TipoDescuento;
import com.sigat.repository.TipoDescuentoRepository;

@Service
public class TipoDescuentoService {

    private final TipoDescuentoRepository tipoDescuentoRepository;

    public TipoDescuentoService(TipoDescuentoRepository tipoDescuentoRepository) {
        this.tipoDescuentoRepository = tipoDescuentoRepository;
    }

    public List<TipoDescuento> getAll() {
        return tipoDescuentoRepository.findAll();
    }

    public List<TipoDescuento> getAllActivos() {
        return tipoDescuentoRepository.findByActivo(1);
    }

    public TipoDescuento getById(Long id) {
        return tipoDescuentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de descuento no encontrado: " + id));
    }

    public TipoDescuento save(TipoDescuento tipoDescuento) {
        return tipoDescuentoRepository.save(tipoDescuento);
    }
}
