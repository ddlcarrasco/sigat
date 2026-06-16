package com.sigat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigat.dto.ApiResponse;
import com.sigat.model.Categoria;
import com.sigat.service.CategoriaService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Categoria>>> getAll(HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                categoriaService.getAll(), request.getRequestURI()));
    }

    @GetMapping("/activas")
    public ResponseEntity<ApiResponse<List<Categoria>>> getAllActivas(HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                categoriaService.getAllActivas(), request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Categoria>> getById(@PathVariable Long id, HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                categoriaService.getById(id), request.getRequestURI()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Categoria>> crear(@RequestBody Categoria categoria,
                                                         HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(HttpStatus.CREATED.value(),
                "Categoria creada correctamente", categoriaService.save(categoria), request.getRequestURI()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Categoria>> actualizar(@PathVariable Long id,
                                                              @RequestBody Categoria datos,
                                                              HttpServletRequest request) {
        Categoria categoria = categoriaService.getById(id);
        categoria.setNombre(datos.getNombre());
        categoria.setDescripcion(datos.getDescripcion());
        categoria.setActivo(datos.getActivo());
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Categoria actualizada correctamente",
                categoriaService.save(categoria), request.getRequestURI()));
    }
}
