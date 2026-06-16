package com.sigat.dto;

import java.time.LocalDate;

public class ReciboRequestDTO {

    private Long contratoId;
    private Integer mes;
    private Integer anio;
    private LocalDate fechaVencimiento;
    private Integer motivoReciboId;

    public ReciboRequestDTO() {}

    public Long getContratoId() { return contratoId; }
    public void setContratoId(Long contratoId) { this.contratoId = contratoId; }

    public Integer getMes() { return mes; }
    public void setMes(Integer mes) { this.mes = mes; }

    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }

    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public Integer getMotivoReciboId() { return motivoReciboId; }
    public void setMotivoReciboId(Integer motivoReciboId) { this.motivoReciboId = motivoReciboId; }
}
