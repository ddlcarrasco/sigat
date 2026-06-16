package com.sigat.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.sigat.model.Recibo;

public class ReciboResponseDTO {

    private Long idrecibo;
    private Integer mes;
    private Integer anio;
    private BigDecimal monto;
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;

    private Long contratoId;
    private String contratoNumero;
    private String titularNombre;

    private Integer tarifaId;

    private Integer estadoReciboId;
    private String estadoReciboNombre;

    private Integer motivoReciboId;
    private String motivoReciboNombre;

    private Long pagoId;
    private String pagoFolio;

    public ReciboResponseDTO(Recibo recibo) {
        this.idrecibo = recibo.getIdrecibo();
        this.mes = recibo.getMes();
        this.anio = recibo.getAnio();
        this.monto = recibo.getMonto();
        this.fechaEmision = recibo.getFechaEmision();
        this.fechaVencimiento = recibo.getFechaVencimiento();

        if (recibo.getContrato() != null) {
            this.contratoId = recibo.getContrato().getIdcontrato();
            this.contratoNumero = recibo.getContrato().getNumeroContrato();
            if (recibo.getContrato().getTitular() != null) {
                this.titularNombre = recibo.getContrato().getTitular().getNombres()
                        + " " + recibo.getContrato().getTitular().getApellido1();
            }
        }

        if (recibo.getTarifa() != null) {
            this.tarifaId = recibo.getTarifa().getIdtarifa();
        }

        if (recibo.getEstadoRecibo() != null) {
            this.estadoReciboId = recibo.getEstadoRecibo().getIdestado_recibo();
            this.estadoReciboNombre = recibo.getEstadoRecibo().getNombre();
        }

        if (recibo.getMotivoRecibo() != null) {
            this.motivoReciboId = recibo.getMotivoRecibo().getIdmotivo_recibo();
            this.motivoReciboNombre = recibo.getMotivoRecibo().getNombre();
        }

        if (recibo.getPago() != null) {
            this.pagoId = recibo.getPago().getIdpago();
            this.pagoFolio = recibo.getPago().getFolio();
        }
    }

    public Long getIdrecibo() { return idrecibo; }
    public Integer getMes() { return mes; }
    public Integer getAnio() { return anio; }
    public BigDecimal getMonto() { return monto; }
    public LocalDate getFechaEmision() { return fechaEmision; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public Long getContratoId() { return contratoId; }
    public String getContratoNumero() { return contratoNumero; }
    public String getTitularNombre() { return titularNombre; }
    public Integer getTarifaId() { return tarifaId; }
    public Integer getEstadoReciboId() { return estadoReciboId; }
    public String getEstadoReciboNombre() { return estadoReciboNombre; }
    public Integer getMotivoReciboId() { return motivoReciboId; }
    public String getMotivoReciboNombre() { return motivoReciboNombre; }
    public Long getPagoId() { return pagoId; }
    public String getPagoFolio() { return pagoFolio; }
}
