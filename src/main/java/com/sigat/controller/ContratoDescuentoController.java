package com.sigat.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigat.dto.ApiResponse;
import com.sigat.dto.ContratoDescuentoResponseDTO;
import com.sigat.service.ContratoDescuentoService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/contratos/{idContrato}/descuentos")
public class ContratoDescuentoController {

    private final ContratoDescuentoService service;

    public ContratoDescuentoController(ContratoDescuentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ContratoDescuentoResponseDTO>>> getByContrato(
            @PathVariable Long idContrato, HttpServletRequest request) {
        List<ContratoDescuentoResponseDTO> lista = service.getByContrato(idContrato).stream()
                .map(ContratoDescuentoResponseDTO::new)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK", lista,
                request.getRequestURI()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ContratoDescuentoResponseDTO>> asignar(
            @PathVariable Long idContrato,
            @RequestBody Map<String, Long> body,
            HttpServletRequest request) {
        Long idTipoDescuento = body.get("idTipoDescuento");
        ContratoDescuentoResponseDTO dto =
                new ContratoDescuentoResponseDTO(service.asignar(idContrato, idTipoDescuento));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(),
                        "Descuento asignado correctamente", dto, request.getRequestURI()));
    }

    @DeleteMapping("/{idContratoDescuento}")
    public ResponseEntity<ApiResponse<Void>> quitar(
            @PathVariable Long idContrato,
            @PathVariable Long idContratoDescuento,
            HttpServletRequest request) {
        service.quitar(idContratoDescuento);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                "Descuento quitado correctamente", null, request.getRequestURI()));
    }
}
