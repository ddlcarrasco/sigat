package com.sigat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigat.dto.ApiResponse;
import com.sigat.model.EstadoTramite;
import com.sigat.service.EstadoTramiteService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/estados-tramite")
public class EstadoTramiteController {

    private final EstadoTramiteService estadoTramiteService;

    public EstadoTramiteController(EstadoTramiteService estadoTramiteService) {
        this.estadoTramiteService = estadoTramiteService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EstadoTramite>>> getAll(HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                estadoTramiteService.getAll(), request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EstadoTramite>> getById(@PathVariable Long id, HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                estadoTramiteService.getById(id), request.getRequestURI()));
    }
}
