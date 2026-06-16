package com.sigat.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sigat.dto.TitularRequestDTO;
import com.sigat.model.Titular;
import com.sigat.repository.TitularRepository;

@Service
public class TitularService {

    private final TitularRepository titularRepository;

    public TitularService(TitularRepository titularRepository) {
        this.titularRepository = titularRepository;
    }

    public List<Titular> getAll() {
        return titularRepository.findAll();
    }

    public Titular getById(Long id) {
        return titularRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Titular no encontrado: " + id));
    }

    public Titular getByCurp(String curp) {
        return titularRepository.findByCurp(curp)
                .orElseThrow(() -> new RuntimeException("Titular con CURP " + curp + " no encontrado"));
    }

    public List<Titular> buscar(String q) {
        return titularRepository
                .findByNombresContainingIgnoreCaseOrApellido1ContainingIgnoreCaseOrApellido2ContainingIgnoreCase(
                        q, q, q);
    }

    public Titular crear(TitularRequestDTO dto) {
        if (dto.getCurp() != null && titularRepository.existsByCurp(dto.getCurp())) {
            throw new RuntimeException("Ya existe un titular con el CURP: " + dto.getCurp());
        }
        Titular titular = new Titular();
        titular.setCurp(dto.getCurp());
        titular.setNombres(dto.getNombres());
        titular.setApellido1(dto.getApellido1());
        titular.setApellido2(dto.getApellido2());
        titular.setTipoIdentificacion(dto.getTipoIdentificacion());
        titular.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        titular.setTelefono(dto.getTelefono());
        titular.setCorreo(dto.getCorreo());
        titular.setFechaAlta(LocalDateTime.now());
        return titularRepository.save(titular);
    }

    public Titular actualizar(Long id, TitularRequestDTO dto) {
        Titular titular = getById(id);
        titular.setNombres(dto.getNombres());
        titular.setApellido1(dto.getApellido1());
        titular.setApellido2(dto.getApellido2());
        titular.setTelefono(dto.getTelefono());
        titular.setCorreo(dto.getCorreo());
        titular.setTipoIdentificacion(dto.getTipoIdentificacion());
        titular.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        // El CURP no se actualiza — es el identificador unico del ciudadano
        return titularRepository.save(titular);
    }
}
