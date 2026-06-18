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
import com.sigat.dto.CambiarEstadoRequestDTO;
import com.sigat.dto.ContratoRequestDTO;
import com.sigat.dto.ContratoResponseDTO;
import com.sigat.service.ContratoService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/contratos")
public class ContratoController {

    private final ContratoService contratoService;

    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    @GetMapping("/siguiente-numero")
    public ResponseEntity<ApiResponse<String>> siguienteNumero(HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                contratoService.generarSiguienteNumero(), request.getRequestURI()));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ContratoResponseDTO>>> getAll(HttpServletRequest request) {
        List<ContratoResponseDTO> lista = contratoService.getAll().stream()
                .map(ContratoResponseDTO::new)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK", lista,
                request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ContratoResponseDTO>> getById(@PathVariable Long id,
                                                                     HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                new ContratoResponseDTO(contratoService.getById(id)), request.getRequestURI()));
    }

    @GetMapping("/numero/{numeroContrato}")
    public ResponseEntity<ApiResponse<ContratoResponseDTO>> getByNumero(
            @PathVariable String numeroContrato, HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                new ContratoResponseDTO(contratoService.getByNumeroContrato(numeroContrato)),
                request.getRequestURI()));
    }

    @GetMapping("/titular/{idTitular}")
    public ResponseEntity<ApiResponse<List<ContratoResponseDTO>>> getByTitular(
            @PathVariable Long idTitular, HttpServletRequest request) {
        List<ContratoResponseDTO> lista = contratoService.getByTitular(idTitular).stream()
                .map(ContratoResponseDTO::new)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK", lista,
                request.getRequestURI()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ContratoResponseDTO>> crear(@RequestBody ContratoRequestDTO dto,
                                                                   HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(HttpStatus.CREATED.value(),
                "Contrato creado correctamente", new ContratoResponseDTO(contratoService.crear(dto)),
                request.getRequestURI()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ContratoResponseDTO>> actualizar(@PathVariable Long id,
                                                                        @RequestBody ContratoRequestDTO dto,
                                                                        HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                "Contrato actualizado correctamente",
                new ContratoResponseDTO(contratoService.actualizar(id, dto)),
                request.getRequestURI()));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<ApiResponse<ContratoResponseDTO>> cambiarEstado(
            @PathVariable Long id, @RequestBody CambiarEstadoRequestDTO dto,
            HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                "Estado del contrato actualizado correctamente",
                new ContratoResponseDTO(contratoService.cambiarEstado(id, dto)),
                request.getRequestURI()));
    }
}
