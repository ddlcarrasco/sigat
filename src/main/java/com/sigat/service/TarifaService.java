package com.sigat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sigat.dto.TarifaRequestDTO;
import com.sigat.model.Categoria;
import com.sigat.model.Sector;
import com.sigat.model.Tarifa;
import com.sigat.repository.CategoriaRepository;
import com.sigat.repository.SectorRepository;
import com.sigat.repository.TarifaRepository;

@Service
public class TarifaService {

    private final TarifaRepository tarifaRepository;
    private final SectorRepository sectorRepository;
    private final CategoriaRepository categoriaRepository;

    public TarifaService(TarifaRepository tarifaRepository,
                         SectorRepository sectorRepository,
                         CategoriaRepository categoriaRepository) {
        this.tarifaRepository = tarifaRepository;
        this.sectorRepository = sectorRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Tarifa> getAll() {
        return tarifaRepository.findAll();
    }

    public List<Tarifa> getAllActivas() {
        return tarifaRepository.findByActiva(1);
    }

    public Tarifa getById(Integer id) {
        return tarifaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarifa no encontrada: " + id));
    }

    // Usado internamente por ReciboService al generar un recibo mensual
    public Tarifa getTarifaVigente(Sector sector, Categoria categoria) {
        return tarifaRepository.findBySectorAndCategoriaAndActiva(sector, categoria, 1).orElseThrow(() -> new RuntimeException(
                        "No hay tarifa activa para el sector " + sector.getNombre()
                        + " y categoria " + categoria.getNombre()));
    }

    // Usado por el controller — recibe IDs en lugar de objetos
    public Tarifa getTarifaVigenteByIds(Long sectorId, Long categoriaId) {
        Sector sector = sectorRepository.findById(sectorId).orElseThrow(() -> new RuntimeException("Sector no encontrado: " + sectorId));
        Categoria categoria = categoriaRepository.findById(categoriaId).orElseThrow(() -> new RuntimeException("Categoria no encontrada: " + categoriaId));
        return getTarifaVigente(sector, categoria);
    }

    public Tarifa crear(TarifaRequestDTO dto) {
        Tarifa tarifa = new Tarifa();
        tarifa.setMontoMensual(dto.getMontoMensual());
        tarifa.setFechaDesde(dto.getFechaDesde());
        tarifa.setFechaHasta(dto.getFechaHasta());
        tarifa.setActiva(dto.getActiva());
        tarifa.setObservaciones(dto.getObservaciones());
        tarifa.setSector(sectorRepository.findById(dto.getSectorId()).orElseThrow(() -> new RuntimeException("Sector no encontrado: " + dto.getSectorId())));
        tarifa.setCategoria(categoriaRepository.findById(dto.getCategoriaId()).orElseThrow(() -> new RuntimeException("Categoria no encontrada: " + dto.getCategoriaId())));
        return tarifaRepository.save(tarifa);
    }

    public Tarifa actualizar(Integer id, TarifaRequestDTO dto) {
        Tarifa tarifa = getById(id);
        tarifa.setMontoMensual(dto.getMontoMensual());
        tarifa.setFechaDesde(dto.getFechaDesde());
        tarifa.setFechaHasta(dto.getFechaHasta());
        tarifa.setActiva(dto.getActiva());
        tarifa.setObservaciones(dto.getObservaciones());
        tarifa.setSector(sectorRepository.findById(dto.getSectorId()).orElseThrow(() -> new RuntimeException("Sector no encontrado: " + dto.getSectorId())));
        tarifa.setCategoria(categoriaRepository.findById(dto.getCategoriaId()).orElseThrow(() -> new RuntimeException("Categoria no encontrada: " + dto.getCategoriaId())));
        return tarifaRepository.save(tarifa);
    }
}
