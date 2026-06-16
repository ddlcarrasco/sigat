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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sigat.dto.ApiResponse;
import com.sigat.dto.TarifaRequestDTO;
import com.sigat.dto.TarifaResponseDTO;
import com.sigat.service.TarifaService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tarifas")
public class TarifaController {

    private final TarifaService tarifaService;

    public TarifaController(TarifaService tarifaService) {
        this.tarifaService = tarifaService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TarifaResponseDTO>>> getAll(HttpServletRequest request) {
        List<TarifaResponseDTO> lista = tarifaService.getAll().stream()
                .map(TarifaResponseDTO::new)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK", lista,
                request.getRequestURI()));
    }

    @GetMapping("/activas")
    public ResponseEntity<ApiResponse<List<TarifaResponseDTO>>> getAllActivas(HttpServletRequest request) {
        List<TarifaResponseDTO> lista = tarifaService.getAllActivas().stream()
                .map(TarifaResponseDTO::new)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK", lista,
                request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TarifaResponseDTO>> getById(@PathVariable Integer id,
                                                                    HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                new TarifaResponseDTO(tarifaService.getById(id)), request.getRequestURI()));
    }

    @GetMapping("/vigente")
    public ResponseEntity<ApiResponse<TarifaResponseDTO>> getVigente(
            @RequestParam Long sectorId,
            @RequestParam Long categoriaId,
            HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                new TarifaResponseDTO(tarifaService.getTarifaVigenteByIds(sectorId, categoriaId)),
                request.getRequestURI()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TarifaResponseDTO>> crear(@RequestBody TarifaRequestDTO dto,
                                                                  HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(HttpStatus.CREATED.value(),
                "Tarifa creada correctamente", new TarifaResponseDTO(tarifaService.crear(dto)),
                request.getRequestURI()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TarifaResponseDTO>> actualizar(@PathVariable Integer id,
                                                                       @RequestBody TarifaRequestDTO dto,
                                                                       HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Tarifa actualizada correctamente",
                new TarifaResponseDTO(tarifaService.actualizar(id, dto)), request.getRequestURI()));
    }
}
