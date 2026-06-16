package com.sigat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigat.dto.ApiResponse;
import com.sigat.model.TipoPago;
import com.sigat.service.TipoPagoService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tipos-pago")
public class TipoPagoController {

    private final TipoPagoService tipoPagoService;

    public TipoPagoController(TipoPagoService tipoPagoService) {
        this.tipoPagoService = tipoPagoService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TipoPago>>> getAll(HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                tipoPagoService.getAll(), request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoPago>> getById(@PathVariable Long id, HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                tipoPagoService.getById(id), request.getRequestURI()));
    }
}
