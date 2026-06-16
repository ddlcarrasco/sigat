package com.sigat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sigat.model.Bitacora;
import com.sigat.model.Usuario;

public interface BitacoraRepository extends JpaRepository<Bitacora, Long> {

    List<Bitacora> findByUsuarioOrderByFechaHoraDesc(Usuario usuario);

    List<Bitacora> findByEntidadAfectadaOrderByFechaHoraDesc(String entidadAfectada);
}
