package com.sigat.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sigat.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Para el login — busca por username
    Optional<Usuario> findByUsername(String username);

    // Para validar que no exista el mismo username al crear un usuario
    boolean existsByUsername(String username);

    // Para filtrar usuarios por nombre de rol (ej. "TECNICO")
    java.util.List<Usuario> findByRol_Nombre(String nombre);
}
