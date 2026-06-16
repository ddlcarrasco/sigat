package com.sigat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigat.dto.ApiResponse;
import com.sigat.dto.CambiarPasswordRequestDTO;
import com.sigat.dto.UsuarioRequestDTO;
import com.sigat.dto.UsuarioResponseDTO;
import com.sigat.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UsuarioResponseDTO>>> getAll(HttpServletRequest request) {
        List<UsuarioResponseDTO> lista = usuarioService.getAll().stream()
                .map(UsuarioResponseDTO::new)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK", lista,
                request.getRequestURI()));
    }

    @GetMapping("/por-rol/{nombre}")
    public ResponseEntity<ApiResponse<List<UsuarioResponseDTO>>> getByRol(@PathVariable String nombre,
                                                                           HttpServletRequest request) {
        List<UsuarioResponseDTO> lista = usuarioService.getByRolNombre(nombre).stream()
                .map(UsuarioResponseDTO::new)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK", lista,
                request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> getById(@PathVariable Long id,
                                                                    HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                new UsuarioResponseDTO(usuarioService.getById(id)), request.getRequestURI()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> crear(@RequestBody UsuarioRequestDTO dto,
                                                                  HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(HttpStatus.CREATED.value(),
                "Usuario creado correctamente", new UsuarioResponseDTO(usuarioService.crear(dto)),
                request.getRequestURI()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> actualizar(@PathVariable Long id,
                                                                       @RequestBody UsuarioRequestDTO dto,
                                                                       HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Usuario actualizado correctamente",
                new UsuarioResponseDTO(usuarioService.actualizar(id, dto)), request.getRequestURI()));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<ApiResponse<Void>> cambiarPassword(@PathVariable Long id,
                                                              @RequestBody CambiarPasswordRequestDTO dto,
                                                              HttpServletRequest request) {
        if (dto.getNuevaPassword() == null || dto.getNuevaPassword().length() < 6) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(400,
                    "La contraseña debe tener al menos 6 caracteres", null, request.getRequestURI()));
        }
        usuarioService.cambiarPassword(id, dto.getNuevaPassword());
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                "Contraseña actualizada correctamente", null, request.getRequestURI()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> desactivar(@PathVariable Long id, HttpServletRequest request) {
        usuarioService.desactivar(id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                "Usuario desactivado correctamente", null, request.getRequestURI()));
    }
}
