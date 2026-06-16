package com.sigat.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.sigat.model.Tarifa;

public class TarifaResponseDTO {

    private Integer idtarifa;
    private BigDecimal montoMensual;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private Integer activa;
    private String observaciones;

    private Long sectorId;
    private String sectorNombre;

    private Long categoriaId;
    private String categoriaNombre;

    public TarifaResponseDTO(Tarifa tarifa) {
        this.idtarifa = tarifa.getIdtarifa();
        this.montoMensual = tarifa.getMontoMensual();
        this.fechaDesde = tarifa.getFechaDesde();
        this.fechaHasta = tarifa.getFechaHasta();
        this.activa = tarifa.getActiva();
        this.observaciones = tarifa.getObservaciones();

        if (tarifa.getSector() != null) {
            this.sectorId = tarifa.getSector().getIdsector();
            this.sectorNombre = tarifa.getSector().getNombre();
        }

        if (tarifa.getCategoria() != null) {
            this.categoriaId = tarifa.getCategoria().getIdcategoria();
            this.categoriaNombre = tarifa.getCategoria().getNombre();
        }
    }

    public Integer getIdtarifa() { return idtarifa; }
    public BigDecimal getMontoMensual() { return montoMensual; }
    public LocalDate getFechaDesde() { return fechaDesde; }
    public LocalDate getFechaHasta() { return fechaHasta; }
    public Integer getActiva() { return activa; }
    public String getObservaciones() { return observaciones; }
    public Long getSectorId() { return sectorId; }
    public String getSectorNombre() { return sectorNombre; }
    public Long getCategoriaId() { return categoriaId; }
    public String getCategoriaNombre() { return categoriaNombre; }
}
