package com.sigat.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sigat.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Para listar solo las categorias activas en el frontend
    List<Categoria> findByActivo(Integer activo);
}
