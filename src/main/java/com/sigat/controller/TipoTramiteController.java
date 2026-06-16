package com.sigat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigat.dto.ApiResponse;
import com.sigat.model.TipoTramite;
import com.sigat.service.TipoTramiteService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tipos-tramite")
public class TipoTramiteController {

    private final TipoTramiteService tipoTramiteService;

    public TipoTramiteController(TipoTramiteService tipoTramiteService) {
        this.tipoTramiteService = tipoTramiteService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TipoTramite>>> getAll(HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                tipoTramiteService.getAll(), request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoTramite>> getById(@PathVariable Long id, HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                tipoTramiteService.getById(id), request.getRequestURI()));
    }
}
