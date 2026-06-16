package com.sigat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigat.dto.ApiResponse;
import com.sigat.dto.ReciboRequestDTO;
import com.sigat.dto.ReciboResponseDTO;
import com.sigat.service.ReciboService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/recibos")
public class ReciboController {

    private final ReciboService reciboService;

    public ReciboController(ReciboService reciboService) {
        this.reciboService = reciboService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReciboResponseDTO>>> getAll(HttpServletRequest request) {
        List<ReciboResponseDTO> lista = reciboService.getAll().stream()
                .map(ReciboResponseDTO::new)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK", lista,
                request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReciboResponseDTO>> getById(@PathVariable Long id,
                                                                   HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                new ReciboResponseDTO(reciboService.getById(id)), request.getRequestURI()));
    }

    @GetMapping("/contrato/{idContrato}")
    public ResponseEntity<ApiResponse<List<ReciboResponseDTO>>> getByContrato(
            @PathVariable Long idContrato, HttpServletRequest request) {
        List<ReciboResponseDTO> lista = reciboService.getByContratoId(idContrato).stream()
                .map(ReciboResponseDTO::new)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK", lista,
                request.getRequestURI()));
    }

    @GetMapping("/contrato/{idContrato}/pendientes")
    public ResponseEntity<ApiResponse<List<ReciboResponseDTO>>> getPendientesByContrato(
            @PathVariable Long idContrato, HttpServletRequest request) {
        List<ReciboResponseDTO> lista = reciboService.getPendientesByContratoId(idContrato).stream()
                .map(ReciboResponseDTO::new)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK", lista,
                request.getRequestURI()));
    }

    @PostMapping("/generar")
    public ResponseEntity<ApiResponse<ReciboResponseDTO>> generar(@RequestBody ReciboRequestDTO dto,
                                                                    HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(HttpStatus.CREATED.value(),
                "Recibo generado correctamente", new ReciboResponseDTO(reciboService.generarReciboMensual(dto)),
                request.getRequestURI()));
    }
}
