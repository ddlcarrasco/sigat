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
import com.sigat.model.TipoDescuento;
import com.sigat.service.TipoDescuentoService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tipos-descuento")
public class TipoDescuentoController {

    private final TipoDescuentoService tipoDescuentoService;

    public TipoDescuentoController(TipoDescuentoService tipoDescuentoService) {
        this.tipoDescuentoService = tipoDescuentoService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TipoDescuento>>> getAll(HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                tipoDescuentoService.getAll(), request.getRequestURI()));
    }

    @GetMapping("/activos")
    public ResponseEntity<ApiResponse<List<TipoDescuento>>> getAllActivos(HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                tipoDescuentoService.getAllActivos(), request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoDescuento>> getById(@PathVariable Long id, HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                tipoDescuentoService.getById(id), request.getRequestURI()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TipoDescuento>> crear(@RequestBody TipoDescuento tipoDescuento,
                                                             HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(HttpStatus.CREATED.value(),
                "Tipo de descuento creado correctamente", tipoDescuentoService.save(tipoDescuento),
                request.getRequestURI()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoDescuento>> actualizar(@PathVariable Long id,
                                                                  @RequestBody TipoDescuento datos,
                                                                  HttpServletRequest request) {
        TipoDescuento td = tipoDescuentoService.getById(id);
        td.setNombre(datos.getNombre());
        td.setPorcentaje(datos.getPorcentaje());
        td.setDescripcion(datos.getDescripcion());
        td.setActivo(datos.getActivo());
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                "Tipo de descuento actualizado correctamente", tipoDescuentoService.save(td),
                request.getRequestURI()));
    }
}
