package com.sigat.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TarifaRequestDTO {

    private BigDecimal montoMensual;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private Integer activa;
    private String observaciones;
    private Long sectorId;
    private Long categoriaId;

    public TarifaRequestDTO() {}

    public BigDecimal getMontoMensual() { return montoMensual; }
    public void setMontoMensual(BigDecimal montoMensual) { this.montoMensual = montoMensual; }

    public LocalDate getFechaDesde() { return fechaDesde; }
    public void setFechaDesde(LocalDate fechaDesde) { this.fechaDesde = fechaDesde; }

    public LocalDate getFechaHasta() { return fechaHasta; }
    public void setFechaHasta(LocalDate fechaHasta) { this.fechaHasta = fechaHasta; }

    public Integer getActiva() { return activa; }
    public void setActiva(Integer activa) { this.activa = activa; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public Long getSectorId() { return sectorId; }
    public void setSectorId(Long sectorId) { this.sectorId = sectorId; }

    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
}
