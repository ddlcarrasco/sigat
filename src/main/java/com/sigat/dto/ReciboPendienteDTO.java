package com.sigat.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.sigat.model.Recibo;
import com.sigat.model.Titular;

public class ReciboPendienteDTO {

    private Long idrecibo;
    private String numeroContrato;
    private String titularNombre;
    private Integer mes;
    private Integer anio;
    private BigDecimal monto;
    private LocalDate fechaVencimiento;
    private String estadoNombre;
    private String sectorNombre;
    private String categoriaNombre;

    public ReciboPendienteDTO(Recibo r) {
        this.idrecibo        = r.getIdrecibo();
        this.mes             = r.getMes();
        this.anio            = r.getAnio();
        this.monto           = r.getMonto();
        this.fechaVencimiento = r.getFechaVencimiento();
        this.estadoNombre    = r.getEstadoRecibo() != null ? r.getEstadoRecibo().getNombre() : null;

        if (r.getContrato() != null) {
            this.numeroContrato = r.getContrato().getNumeroContrato();
            this.sectorNombre   = r.getContrato().getSector()   != null ? r.getContrato().getSector().getNombre()   : null;
            this.categoriaNombre = r.getContrato().getCategoria() != null ? r.getContrato().getCategoria().getNombre() : null;

            Titular t = r.getContrato().getTitular();
            if (t != null) {
                this.titularNombre = (t.getNombres() != null ? t.getNombres() : "")
                    + (t.getApellido1() != null ? " " + t.getApellido1() : "")
                    + (t.getApellido2() != null ? " " + t.getApellido2() : "");
                this.titularNombre = this.titularNombre.trim();
            }
        }
    }

    public Long getIdrecibo() { return idrecibo; }
    public String getNumeroContrato() { return numeroContrato; }
    public String getTitularNombre() { return titularNombre; }
    public Integer getMes() { return mes; }
    public Integer getAnio() { return anio; }
    public BigDecimal getMonto() { return monto; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public String getEstadoNombre() { return estadoNombre; }
    public String getSectorNombre() { return sectorNombre; }
    public String getCategoriaNombre() { return categoriaNombre; }
}
