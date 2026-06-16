package com.sigat.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sigat.model.Categoria;
import com.sigat.repository.CategoriaRepository;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> getAll() {
        return categoriaRepository.findAll();
    }

    public List<Categoria> getAllActivas() {
        return categoriaRepository.findByActivo(1);
    }

    public Categoria getById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada: " + id));
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
}
