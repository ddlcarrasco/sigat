package com.sigat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigat.dto.ApiResponse;
import com.sigat.dto.ResolverTramiteRequestDTO;
import com.sigat.dto.TramiteRequestDTO;
import com.sigat.dto.TramiteResponseDTO;
import com.sigat.dto.VoBoRequestDTO;
import com.sigat.service.TramiteService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tramites")
public class TramiteController {

    private final TramiteService tramiteService;

    public TramiteController(TramiteService tramiteService) {
        this.tramiteService = tramiteService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TramiteResponseDTO>>> getAll(HttpServletRequest request) {
        List<TramiteResponseDTO> lista = tramiteService.getAll().stream()
                .map(TramiteResponseDTO::new)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK", lista,
                request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TramiteResponseDTO>> getById(@PathVariable Long id,
                                                                    HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                new TramiteResponseDTO(tramiteService.getById(id)), request.getRequestURI()));
    }

    @GetMapping("/pendientes")
    public ResponseEntity<ApiResponse<List<TramiteResponseDTO>>> getPendientes(HttpServletRequest request) {
        List<TramiteResponseDTO> lista = tramiteService.getPendientes().stream()
                .map(TramiteResponseDTO::new)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK", lista,
                request.getRequestURI()));
    }

    // Lista los tramites que ya tienen VoBo y estan esperando ser resueltos
    @GetMapping("/pendientes-vobo")
    public ResponseEntity<ApiResponse<List<TramiteResponseDTO>>> getPendientesVoBo(HttpServletRequest request) {
        List<TramiteResponseDTO> lista = tramiteService.getPendientesVoBo().stream()
                .map(TramiteResponseDTO::new)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK", lista,
                request.getRequestURI()));
    }

    @GetMapping("/contrato/{idContrato}")
    public ResponseEntity<ApiResponse<List<TramiteResponseDTO>>> getByContrato(
            @PathVariable Long idContrato, HttpServletRequest request) {
        List<TramiteResponseDTO> lista = tramiteService.getByContratoId(idContrato).stream()
                .map(TramiteResponseDTO::new)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK", lista,
                request.getRequestURI()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TramiteResponseDTO>> crear(@RequestBody TramiteRequestDTO dto,
                                                                  @AuthenticationPrincipal UserDetails userDetails,
                                                                  HttpServletRequest request) {
        ApiResponse<TramiteResponseDTO> response = new ApiResponse<>(HttpStatus.CREATED.value(),
                "Tramite creado correctamente",
                new TramiteResponseDTO(tramiteService.crear(dto, userDetails.getUsername())),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Solo DIRECTOR (y ADMIN): aprueba o rechaza en etapa de VoBo
    @PutMapping("/{id}/vobo")
    public ResponseEntity<ApiResponse<TramiteResponseDTO>> vobo(
            @PathVariable Long id, @RequestBody VoBoRequestDTO dto,
            @AuthenticationPrincipal UserDetails userDetails,
            HttpServletRequest request) {
        ApiResponse<TramiteResponseDTO> response = new ApiResponse<>(HttpStatus.OK.value(),
                "VoBo registrado correctamente",
                new TramiteResponseDTO(tramiteService.vobo(id, dto, userDetails.getUsername())),
                request.getRequestURI());
        return ResponseEntity.ok(response);
    }

    // OPERADOR y TECNICO: resuelve o rechaza el tramite
    @PutMapping("/{id}/resolver")
    public ResponseEntity<ApiResponse<TramiteResponseDTO>> resolver(
            @PathVariable Long id, @RequestBody ResolverTramiteRequestDTO dto,
            @AuthenticationPrincipal UserDetails userDetails,
            HttpServletRequest request) {
        ApiResponse<TramiteResponseDTO> response = new ApiResponse<>(HttpStatus.OK.value(),
                "Tramite resuelto correctamente",
                new TramiteResponseDTO(tramiteService.resolver(id, dto, userDetails.getUsername())),
                request.getRequestURI());
        return ResponseEntity.ok(response);
    }
}
