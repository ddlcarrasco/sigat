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
import com.sigat.dto.TitularRequestDTO;
import com.sigat.dto.TitularResponseDTO;
import com.sigat.service.TitularService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/titulares")
public class TitularController {

    private final TitularService titularService;

    public TitularController(TitularService titularService) {
        this.titularService = titularService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TitularResponseDTO>>> getAll(HttpServletRequest request) {
        List<TitularResponseDTO> lista = titularService.getAll().stream()
                .map(TitularResponseDTO::new)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK", lista,
                request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TitularResponseDTO>> getById(@PathVariable Long id,
                                                                    HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                new TitularResponseDTO(titularService.getById(id)), request.getRequestURI()));
    }

    @GetMapping("/curp/{curp}")
    public ResponseEntity<ApiResponse<TitularResponseDTO>> getByCurp(@PathVariable String curp,
                                                                       HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                new TitularResponseDTO(titularService.getByCurp(curp)), request.getRequestURI()));
    }

    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<List<TitularResponseDTO>>> buscar(@RequestParam String q,
                                                                         HttpServletRequest request) {
        List<TitularResponseDTO> lista = titularService.buscar(q).stream()
                .map(TitularResponseDTO::new)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK", lista,
                request.getRequestURI()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TitularResponseDTO>> crear(@RequestBody TitularRequestDTO dto,
                                                                  HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(HttpStatus.CREATED.value(),
                "Titular creado correctamente", new TitularResponseDTO(titularService.crear(dto)),
                request.getRequestURI()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TitularResponseDTO>> actualizar(@PathVariable Long id,
                                                                       @RequestBody TitularRequestDTO dto,
                                                                       HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Titular actualizado correctamente",
                new TitularResponseDTO(titularService.actualizar(id, dto)), request.getRequestURI()));
    }
}
