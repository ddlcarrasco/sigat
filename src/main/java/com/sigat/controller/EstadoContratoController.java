package com.sigat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigat.dto.ApiResponse;
import com.sigat.model.EstadoContrato;
import com.sigat.service.EstadoContratoService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/estados-contrato")
public class EstadoContratoController {

    private final EstadoContratoService estadoContratoService;

    public EstadoContratoController(EstadoContratoService estadoContratoService) {
        this.estadoContratoService = estadoContratoService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EstadoContrato>>> getAll(HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                estadoContratoService.getAll(), request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EstadoContrato>> getById(@PathVariable Long id, HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                estadoContratoService.getById(id), request.getRequestURI()));
    }
}
