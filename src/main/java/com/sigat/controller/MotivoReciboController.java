package com.sigat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigat.dto.ApiResponse;
import com.sigat.model.MotivoRecibo;
import com.sigat.service.MotivoReciboService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/motivos-recibo")
public class MotivoReciboController {

    private final MotivoReciboService motivoReciboService;

    public MotivoReciboController(MotivoReciboService motivoReciboService) {
        this.motivoReciboService = motivoReciboService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MotivoRecibo>>> getAll(HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                motivoReciboService.getAll(), request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MotivoRecibo>> getById(@PathVariable Integer id, HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                motivoReciboService.getById(id), request.getRequestURI()));
    }
}
