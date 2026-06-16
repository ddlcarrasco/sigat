package com.sigat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigat.dto.ApiResponse;
import com.sigat.model.EstadoRecibo;
import com.sigat.service.EstadoReciboService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/estados-recibo")
public class EstadoReciboController {

    private final EstadoReciboService estadoReciboService;

    public EstadoReciboController(EstadoReciboService estadoReciboService) {
        this.estadoReciboService = estadoReciboService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EstadoRecibo>>> getAll(HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                estadoReciboService.getAll(), request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EstadoRecibo>> getById(@PathVariable Integer id, HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                estadoReciboService.getById(id), request.getRequestURI()));
    }
}
