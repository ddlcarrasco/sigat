package com.sigat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigat.dto.ApiResponse;
import com.sigat.dto.PagoDetalleDTO;
import com.sigat.dto.PagoRequestDTO;
import com.sigat.dto.PagoResponseDTO;
import com.sigat.service.PagoService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PagoResponseDTO>>> getAll(HttpServletRequest request) {
        List<PagoResponseDTO> lista = pagoService.getAll().stream()
                .map(PagoResponseDTO::new)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK", lista,
                request.getRequestURI()));
    }

    @GetMapping("/{id}/detalle")
    public ResponseEntity<ApiResponse<PagoDetalleDTO>> getDetalle(@PathVariable Long id,
                                                                    HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                pagoService.getDetalle(id), request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PagoResponseDTO>> getById(@PathVariable Long id,
                                                                 HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                new PagoResponseDTO(pagoService.getById(id)), request.getRequestURI()));
    }

    @GetMapping("/folio/{folio}")
    public ResponseEntity<ApiResponse<PagoResponseDTO>> getByFolio(@PathVariable String folio,
                                                                     HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                new PagoResponseDTO(pagoService.getByFolio(folio)), request.getRequestURI()));
    }

    @PostMapping("/registrar")
    public ResponseEntity<ApiResponse<PagoResponseDTO>> registrar(@RequestBody PagoRequestDTO dto,
                                                                    @AuthenticationPrincipal UserDetails userDetails,
                                                                    HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(HttpStatus.CREATED.value(),
                "Pago registrado correctamente",
                new PagoResponseDTO(pagoService.registrarPago(dto, userDetails.getUsername())),
                request.getRequestURI()));
    }
}
