package com.sigat.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tarifa")
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtarifa")
    private Integer idtarifa;

    @Column(name = "monto_mensual")
    private BigDecimal montoMensual;

    @Column(name = "fechaDesde")
    private LocalDate fechaDesde;

    @Column(name = "fechaHasta")
    private LocalDate fechaHasta;

    @Column(name = "activa", columnDefinition = "tinyint")
    private Integer activa;

    @Column(name = "observaciones")
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "sector_idsector")
    private Sector sector;

    @ManyToOne
    @JoinColumn(name = "categoria_idcategoria")
    private Categoria categoria;

    public Tarifa() {}

    public Integer getIdtarifa() { return idtarifa; }
    public void setIdtarifa(Integer idtarifa) { this.idtarifa = idtarifa; }

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

    public Sector getSector() { return sector; }
    public void setSector(Sector sector) { this.sector = sector; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
