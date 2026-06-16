package com.sigat.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sigat.dto.CambiarEstadoRequestDTO;
import com.sigat.dto.ContratoRequestDTO;
import com.sigat.model.Contrato;
import com.sigat.model.EstadoContrato;
import com.sigat.model.HistorialEstadoContrato;
import com.sigat.model.Titular;
import com.sigat.model.Usuario;
import com.sigat.repository.CategoriaRepository;
import com.sigat.repository.ContratoRepository;
import com.sigat.repository.EstadoContratoRepository;
import com.sigat.repository.HistorialEstadoContratoRepository;
import com.sigat.repository.SectorRepository;
import com.sigat.repository.TitularRepository;
import com.sigat.repository.UsuarioRepository;

@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;
    private final HistorialEstadoContratoRepository historialRepository;
    private final TitularRepository titularRepository;
    private final SectorRepository sectorRepository;
    private final CategoriaRepository categoriaRepository;
    private final EstadoContratoRepository estadoContratoRepository;
    private final UsuarioRepository usuarioRepository;

    public ContratoService(ContratoRepository contratoRepository,
                           HistorialEstadoContratoRepository historialRepository,
                           TitularRepository titularRepository,
                           SectorRepository sectorRepository,
                           CategoriaRepository categoriaRepository,
                           EstadoContratoRepository estadoContratoRepository,
                           UsuarioRepository usuarioRepository) {
        this.contratoRepository = contratoRepository;
        this.historialRepository = historialRepository;
        this.titularRepository = titularRepository;
        this.sectorRepository = sectorRepository;
        this.categoriaRepository = categoriaRepository;
        this.estadoContratoRepository = estadoContratoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Contrato> getAll() {
        return contratoRepository.findAll();
    }

    public Contrato getById(Long id) {
        return contratoRepository.findById(id).orElseThrow(() -> new RuntimeException("Contrato no encontrado: " + id));
    }

    public Contrato getByNumeroContrato(String numeroContrato) {
        return contratoRepository.findByNumeroContrato(numeroContrato).orElseThrow(() -> new RuntimeException("Contrato no encontrado: " + numeroContrato));
    }

    public List<Contrato> getByTitular(Long idTitular) {
        Titular titular = titularRepository.findById(idTitular).orElseThrow(() -> new RuntimeException("Titular no encontrado: " + idTitular));
        return contratoRepository.findByTitular(titular);
    }

    public Contrato crear(ContratoRequestDTO dto) {
        if (contratoRepository.existsByNumeroContrato(dto.getNumeroContrato())) {
            throw new RuntimeException("Ya existe un contrato con ese numero: " + dto.getNumeroContrato());
        }

        Contrato contrato = new Contrato();
        contrato.setNumeroContrato(dto.getNumeroContrato());
        contrato.setNumeroCatastro(dto.getNumeroCatastro());
        contrato.setDomicilioToma(dto.getDomicilioToma());
        contrato.setFechaInstalacion(dto.getFechaInstalacion());
        contrato.setObservaciones(dto.getObservaciones());
        contrato.setCreatedAt(LocalDateTime.now());
        contrato.setTitular(titularRepository.findById(dto.getTitularId()).orElseThrow(() -> new RuntimeException("Titular no encontrado: " + dto.getTitularId())));
        contrato.setSector(sectorRepository.findById(dto.getSectorId()).orElseThrow(() -> new RuntimeException("Sector no encontrado: " + dto.getSectorId())));
        contrato.setCategoria(categoriaRepository.findById(dto.getCategoriaId()).orElseThrow(() -> new RuntimeException("Categoria no encontrada: " + dto.getCategoriaId())));
        contrato.setEstadoContrato(estadoContratoRepository.findById(dto.getEstadoContratoId()).orElseThrow(() -> new RuntimeException("Estado de contrato no encontrado: " + dto.getEstadoContratoId())));

        if (dto.getUsuarioInstaladorId() != null) {
            contrato.setUsuarioInstalador(usuarioRepository.findById(dto.getUsuarioInstaladorId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + dto.getUsuarioInstaladorId())));
        }

        return contratoRepository.save(contrato);
    }

    public Contrato actualizar(Long id, ContratoRequestDTO dto) {
        Contrato contrato = getById(id);
        contrato.setNumeroContrato(dto.getNumeroContrato());
        contrato.setNumeroCatastro(dto.getNumeroCatastro());
        contrato.setDomicilioToma(dto.getDomicilioToma());
        contrato.setObservaciones(dto.getObservaciones());
        contrato.setTitular(titularRepository.findById(dto.getTitularId()).orElseThrow(() -> new RuntimeException("Titular no encontrado: " + dto.getTitularId())));
        contrato.setSector(sectorRepository.findById(dto.getSectorId()).orElseThrow(() -> new RuntimeException("Sector no encontrado: " + dto.getSectorId())));
        contrato.setCategoria(categoriaRepository.findById(dto.getCategoriaId()).orElseThrow(() -> new RuntimeException("Categoria no encontrada: " + dto.getCategoriaId())));
        if (dto.getUsuarioInstaladorId() != null) {
            contrato.setUsuarioInstalador(usuarioRepository.findById(dto.getUsuarioInstaladorId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + dto.getUsuarioInstaladorId())));
        }
        return contratoRepository.save(contrato);
    }

    // Cambia el estado del contrato y registra el cambio en el historial
    public Contrato cambiarEstado(Long idContrato, CambiarEstadoRequestDTO dto) {
        Contrato contrato = getById(idContrato);
        EstadoContrato estadoAnterior = contrato.getEstadoContrato();

        EstadoContrato estadoNuevo = estadoContratoRepository.findById(dto.getIdEstadoNuevo()).orElseThrow(() -> new RuntimeException("Estado de contrato no encontrado: " + dto.getIdEstadoNuevo()));
        Usuario usuarioResponsable = usuarioRepository.findById(dto.getIdUsuarioResponsable()).orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + dto.getIdUsuarioResponsable()));

        contrato.setEstadoContrato(estadoNuevo);
        contratoRepository.save(contrato);

        HistorialEstadoContrato entrada = new HistorialEstadoContrato();
        entrada.setContrato(contrato);
        entrada.setEstadoAnterior(estadoAnterior);
        entrada.setEstadoNuevo(estadoNuevo);
        entrada.setObservaciones(dto.getObservaciones());
        entrada.setFecha(LocalDateTime.now());
        entrada.setUsuario(usuarioResponsable);
        historialRepository.save(entrada);

        return contrato;
    }
}
