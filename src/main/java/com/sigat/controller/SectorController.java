package com.sigat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigat.dto.ApiResponse;
import com.sigat.model.Sector;
import com.sigat.service.SectorService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sectores")
public class SectorController {

    private final SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Sector>>> getAll(HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                sectorService.getAll(), request.getRequestURI()));
    }

    @GetMapping("/activos")
    public ResponseEntity<ApiResponse<List<Sector>>> getAllActivos(HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                sectorService.getAllActivos(), request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Sector>> getById(@PathVariable Long id, HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                sectorService.getById(id), request.getRequestURI()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Sector>> crear(@RequestBody Sector sector, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(HttpStatus.CREATED.value(),
                "Sector creado correctamente", sectorService.save(sector), request.getRequestURI()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Sector>> actualizar(@PathVariable Long id,
                                                           @RequestBody Sector datos,
                                                           HttpServletRequest request) {
        Sector sector = sectorService.getById(id);
        sector.setNombre(datos.getNombre());
        sector.setDescripcion(datos.getDescripcion());
        sector.setActivo(datos.getActivo());
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Sector actualizado correctamente",
                sectorService.save(sector), request.getRequestURI()));
    }
}
