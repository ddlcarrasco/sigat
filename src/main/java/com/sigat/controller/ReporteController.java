package com.sigat.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sigat.dto.ApiResponse;
import com.sigat.dto.ReciboPendienteDTO;
import com.sigat.dto.ResumenReporteDTO;
import com.sigat.service.ReporteService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/resumen")
    public ResponseEntity<ApiResponse<ResumenReporteDTO>> resumen(HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                reporteService.resumen(), request.getRequestURI()));
    }

    @GetMapping("/cobros-por-mes")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> cobrosPorMes(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,
            HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                reporteService.cobrosPorMes(desde, hasta), request.getRequestURI()));
    }

    @GetMapping("/contratos-por-estado")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> contratosPorEstado(HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                reporteService.contratosPorEstado(), request.getRequestURI()));
    }

    @GetMapping("/tramites-por-estado")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> tramitesPorEstado(HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                reporteService.tramitesPorEstado(), request.getRequestURI()));
    }

    @GetMapping("/tramites-por-tipo")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> tramitesPorTipo(HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                reporteService.tramitesPorTipo(), request.getRequestURI()));
    }

    @GetMapping("/recibos-pendientes")
    public ResponseEntity<ApiResponse<List<ReciboPendienteDTO>>> recibosPendientes(HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                reporteService.recibosPendientes(), request.getRequestURI()));
    }

    @GetMapping("/ingresos-por-sector")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> ingresosPorSector(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,
            HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                reporteService.ingresosPorSector(desde, hasta), request.getRequestURI()));
    }

    @GetMapping("/ingresos-por-categoria")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> ingresosPorCategoria(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,
            HttpServletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "OK",
                reporteService.ingresosPorCategoria(desde, hasta), request.getRequestURI()));
    }
}
