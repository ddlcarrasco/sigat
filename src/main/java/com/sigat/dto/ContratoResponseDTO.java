package com.sigat.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.sigat.model.Contrato;

public class ContratoResponseDTO {

    private Long idcontrato;
    private String numeroContrato;
    private String numeroCatastro;
    private String domicilioToma;
    private LocalDate fechaInstalacion;
    private String observaciones;
    private LocalDateTime createdAt;

    private Long titularId;
    private String titularNombre;

    private Long sectorId;
    private String sectorNombre;

    private Long categoriaId;
    private String categoriaNombre;

    private Long estadoContratoId;
    private String estadoContratoNombre;

    private Long usuarioInstaladorId;
    private String usuarioInstaladorNombre;

    public ContratoResponseDTO(Contrato contrato) {
        this.idcontrato = contrato.getIdcontrato();
        this.numeroContrato = contrato.getNumeroContrato();
        this.numeroCatastro = contrato.getNumeroCatastro();
        this.domicilioToma = contrato.getDomicilioToma();
        this.fechaInstalacion = contrato.getFechaInstalacion();
        this.observaciones = contrato.getObservaciones();
        this.createdAt = contrato.getCreatedAt();

        if (contrato.getTitular() != null) {
            this.titularId = contrato.getTitular().getIdtitular();
            this.titularNombre = contrato.getTitular().getNombres()
                    + " " + contrato.getTitular().getApellido1()
                    + (contrato.getTitular().getApellido2() != null
                            ? " " + contrato.getTitular().getApellido2() : "");
        }

        if (contrato.getSector() != null) {
            this.sectorId = contrato.getSector().getIdsector();
            this.sectorNombre = contrato.getSector().getNombre();
        }

        if (contrato.getCategoria() != null) {
            this.categoriaId = contrato.getCategoria().getIdcategoria();
            this.categoriaNombre = contrato.getCategoria().getNombre();
        }

        if (contrato.getEstadoContrato() != null) {
            this.estadoContratoId = contrato.getEstadoContrato().getIdestado_contrato();
            this.estadoContratoNombre = contrato.getEstadoContrato().getNombre();
        }

        if (contrato.getUsuarioInstalador() != null) {
            this.usuarioInstaladorId = contrato.getUsuarioInstalador().getIdusuario();
            this.usuarioInstaladorNombre = contrato.getUsuarioInstalador().getNombres()
                    + " " + contrato.getUsuarioInstalador().getApellidoPaterno();
        }
    }

    public Long getIdcontrato() { return idcontrato; }
    public String getNumeroContrato() { return numeroContrato; }
    public String getNumeroCatastro() { return numeroCatastro; }
    public String getDomicilioToma() { return domicilioToma; }
    public LocalDate getFechaInstalacion() { return fechaInstalacion; }
    public String getObservaciones() { return observaciones; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Long getTitularId() { return titularId; }
    public String getTitularNombre() { return titularNombre; }
    public Long getSectorId() { return sectorId; }
    public String getSectorNombre() { return sectorNombre; }
    public Long getCategoriaId() { return categoriaId; }
    public String getCategoriaNombre() { return categoriaNombre; }
    public Long getEstadoContratoId() { return estadoContratoId; }
    public String getEstadoContratoNombre() { return estadoContratoNombre; }
    public Long getUsuarioInstaladorId() { return usuarioInstaladorId; }
    public String getUsuarioInstaladorNombre() { return usuarioInstaladorNombre; }
}
