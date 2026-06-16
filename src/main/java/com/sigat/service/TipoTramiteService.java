package com.sigat.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sigat.model.TipoTramite;
import com.sigat.repository.TipoTramiteRepository;

@Service
public class TipoTramiteService {

    private final TipoTramiteRepository tipoTramiteRepository;

    public TipoTramiteService(TipoTramiteRepository tipoTramiteRepository) {
        this.tipoTramiteRepository = tipoTramiteRepository;
    }

    public List<TipoTramite> getAll() {
        return tipoTramiteRepository.findAll();
    }

    public TipoTramite getById(Long id) {
        return tipoTramiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de tramite no encontrado: " + id));
    }
}
