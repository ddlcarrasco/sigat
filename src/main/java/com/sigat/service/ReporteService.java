package com.sigat.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sigat.dto.ReciboPendienteDTO;
import com.sigat.dto.ResumenReporteDTO;
import com.sigat.model.Recibo;
import com.sigat.model.Tramite;
import com.sigat.repository.ContratoRepository;
import com.sigat.repository.ReciboRepository;
import com.sigat.repository.TramiteRepository;

@Service
public class ReporteService {

    private final ReciboRepository   reciboRepo;
    private final ContratoRepository contratoRepo;
    private final TramiteRepository  tramiteRepo;

    public ReporteService(ReciboRepository reciboRepo,
                          ContratoRepository contratoRepo,
                          TramiteRepository tramiteRepo) {
        this.reciboRepo   = reciboRepo;
        this.contratoRepo = contratoRepo;
        this.tramiteRepo  = tramiteRepo;
    }

    // ── Resumen general ───────────────────────────────────────────────────────

    public ResumenReporteDTO resumen() {
        LocalDateTime ahora     = LocalDateTime.now();
        int mesActual           = ahora.getMonthValue();
        int anioActual          = ahora.getYear();
        LocalDateTime inicioMes = LocalDateTime.of(anioActual, mesActual, 1, 0, 0);
        LocalDateTime inicioAnio = LocalDateTime.of(anioActual, 1, 1, 0, 0);

        List<Recibo> pagadosAnio = reciboRepo.findByPagoIsNotNullAndPagoFechaPagoBetween(inicioAnio, ahora);
        List<Recibo> pagadosMes  = pagadosAnio.stream()
                .filter(r -> r.getPago().getFechaPago().getMonthValue() == mesActual)
                .toList();

        BigDecimal cobradoAnio = pagadosAnio.stream()
                .map(r -> r.getPago().getMontoRecibido())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal cobradoMes = pagadosMes.stream()
                .map(r -> r.getPago().getMontoRecibido())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        ResumenReporteDTO dto = new ResumenReporteDTO();
        dto.setTotalContratos(contratoRepo.count());
        dto.setContratosActivos(contratoRepo.countByEstadoContratoNombreIgnoreCase("activo"));
        dto.setRecibosPendientes(reciboRepo.countByPagoIsNull());
        dto.setTramitesPendientes(tramiteRepo.countByEstadoTramiteNombreIgnoreCase("pendiente"));
        dto.setMontoCobradoMesActual(cobradoMes);
        dto.setMontoCobradoAnioActual(cobradoAnio);
        return dto;
    }

    // ── Cobros por mes (en rango de fechas) ───────────────────────────────────

    public List<Map<String, Object>> cobrosPorMes(LocalDate desde, LocalDate hasta) {
        LocalDateTime desdeT = desde.atStartOfDay();
        LocalDateTime hastaT = hasta.atTime(23, 59, 59);

        List<Recibo> pagados = reciboRepo.findByPagoIsNotNullAndPagoFechaPagoBetween(desdeT, hastaT);

        // Agrupa por "YYYY-MM" y suma montoRecibido
        Map<String, BigDecimal> agrupado = new LinkedHashMap<>();
        pagados.stream()
               .sorted((a, b) -> a.getPago().getFechaPago().compareTo(b.getPago().getFechaPago()))
               .forEach(r -> {
                   LocalDateTime fp = r.getPago().getFechaPago();
                   String clave = fp.getYear() + "-" + String.format("%02d", fp.getMonthValue());
                   agrupado.merge(clave, r.getPago().getMontoRecibido(), BigDecimal::add);
               });

        return agrupado.entrySet().stream()
                .map(e -> Map.<String, Object>of("periodo", e.getKey(), "total", e.getValue()))
                .collect(Collectors.toList());
    }

    // ── Contratos por estado ──────────────────────────────────────────────────

    public List<Map<String, Object>> contratosPorEstado() {
        return contratoRepo.findAll().stream()
                .filter(c -> c.getEstadoContrato() != null)
                .collect(Collectors.groupingBy(
                        c -> c.getEstadoContrato().getNombre(),
                        Collectors.counting()))
                .entrySet().stream()
                .map(e -> Map.<String, Object>of("nombre", e.getKey(), "cantidad", e.getValue()))
                .sorted((a, b) -> Long.compare((Long) b.get("cantidad"), (Long) a.get("cantidad")))
                .collect(Collectors.toList());
    }

    // ── Trámites por estado ───────────────────────────────────────────────────

    public List<Map<String, Object>> tramitesPorEstado() {
        return tramiteRepo.findAll().stream()
                .filter(t -> t.getEstadoTramite() != null)
                .collect(Collectors.groupingBy(
                        t -> t.getEstadoTramite().getNombre(),
                        Collectors.counting()))
                .entrySet().stream()
                .map(e -> Map.<String, Object>of("nombre", e.getKey(), "cantidad", e.getValue()))
                .sorted((a, b) -> Long.compare((Long) b.get("cantidad"), (Long) a.get("cantidad")))
                .collect(Collectors.toList());
    }

    // ── Trámites por tipo ─────────────────────────────────────────────────────

    public List<Map<String, Object>> tramitesPorTipo() {
        return tramiteRepo.findAll().stream()
                .filter(t -> t.getTipoTramite() != null)
                .collect(Collectors.groupingBy(
                        t -> t.getTipoTramite().getNombre(),
                        Collectors.counting()))
                .entrySet().stream()
                .map(e -> Map.<String, Object>of("nombre", e.getKey(), "cantidad", e.getValue()))
                .sorted((a, b) -> Long.compare((Long) b.get("cantidad"), (Long) a.get("cantidad")))
                .collect(Collectors.toList());
    }

    // ── Recibos pendientes ────────────────────────────────────────────────────

    public List<ReciboPendienteDTO> recibosPendientes() {
        return reciboRepo.findByPagoIsNull().stream()
                .map(ReciboPendienteDTO::new)
                .collect(Collectors.toList());
    }

    // ── Ingresos por sector ───────────────────────────────────────────────────

    public List<Map<String, Object>> ingresosPorSector(LocalDate desde, LocalDate hasta) {
        LocalDateTime desdeT = desde.atStartOfDay();
        LocalDateTime hastaT = hasta.atTime(23, 59, 59);

        Map<String, BigDecimal> agrupado = new LinkedHashMap<>();
        reciboRepo.findByPagoIsNotNullAndPagoFechaPagoBetween(desdeT, hastaT).stream()
                .filter(r -> r.getContrato() != null && r.getContrato().getSector() != null)
                .forEach(r -> {
                    String sector = r.getContrato().getSector().getNombre();
                    agrupado.merge(sector, r.getPago().getMontoRecibido(), BigDecimal::add);
                });

        return agrupado.entrySet().stream()
                .map(e -> Map.<String, Object>of("nombre", e.getKey(), "total", e.getValue()))
                .sorted((a, b) -> ((BigDecimal) b.get("total")).compareTo((BigDecimal) a.get("total")))
                .collect(Collectors.toList());
    }

    // ── Ingresos por categoría ────────────────────────────────────────────────

    public List<Map<String, Object>> ingresosPorCategoria(LocalDate desde, LocalDate hasta) {
        LocalDateTime desdeT = desde.atStartOfDay();
        LocalDateTime hastaT = hasta.atTime(23, 59, 59);

        Map<String, BigDecimal> agrupado = new LinkedHashMap<>();
        reciboRepo.findByPagoIsNotNullAndPagoFechaPagoBetween(desdeT, hastaT).stream()
                .filter(r -> r.getContrato() != null && r.getContrato().getCategoria() != null)
                .forEach(r -> {
                    String cat = r.getContrato().getCategoria().getNombre();
                    agrupado.merge(cat, r.getPago().getMontoRecibido(), BigDecimal::add);
                });

        return agrupado.entrySet().stream()
                .map(e -> Map.<String, Object>of("nombre", e.getKey(), "total", e.getValue()))
                .sorted((a, b) -> ((BigDecimal) b.get("total")).compareTo((BigDecimal) a.get("total")))
                .collect(Collectors.toList());
    }
}
