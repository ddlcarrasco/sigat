package com.sigat.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sigat.model.Rol;
import com.sigat.repository.RolRepository;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Rol> getAll() {
        return rolRepository.findAll();
    }

    public Rol getById(Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + id));
    }
}
