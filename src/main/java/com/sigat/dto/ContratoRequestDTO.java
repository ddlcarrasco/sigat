package com.sigat.dto;

import java.time.LocalDate;

public class ContratoRequestDTO {

    private String numeroContrato;
    private String numeroCatastro;
    private String domicilioToma;
    private LocalDate fechaInstalacion;
    private String observaciones;
    private Long titularId;
    private Long sectorId;
    private Long categoriaId;
    private Long estadoContratoId;
    private Long usuarioInstaladorId;

    public ContratoRequestDTO() {}

    public String getNumeroContrato() { return numeroContrato; }
    public void setNumeroContrato(String numeroContrato) { this.numeroContrato = numeroContrato; }

    public String getNumeroCatastro() { return numeroCatastro; }
    public void setNumeroCatastro(String numeroCatastro) { this.numeroCatastro = numeroCatastro; }

    public String getDomicilioToma() { return domicilioToma; }
    public void setDomicilioToma(String domicilioToma) { this.domicilioToma = domicilioToma; }

    public LocalDate getFechaInstalacion() { return fechaInstalacion; }
    public void setFechaInstalacion(LocalDate fechaInstalacion) { this.fechaInstalacion = fechaInstalacion; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public Long getTitularId() { return titularId; }
    public void setTitularId(Long titularId) { this.titularId = titularId; }

    public Long getSectorId() { return sectorId; }
    public void setSectorId(Long sectorId) { this.sectorId = sectorId; }

    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }

    public Long getEstadoContratoId() { return estadoContratoId; }
    public void setEstadoContratoId(Long estadoContratoId) { this.estadoContratoId = estadoContratoId; }

    public Long getUsuarioInstaladorId() { return usuarioInstaladorId; }
    public void setUsuarioInstaladorId(Long usuarioInstaladorId) { this.usuarioInstaladorId = usuarioInstaladorId; }
}
