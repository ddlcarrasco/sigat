package com.sigat.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contrato")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcontrato")
    private Long idcontrato;

    @Column(name = "numero_contrato")
    private String numeroContrato;

    @Column(name = "numero_catastro")
    private String numeroCatastro;

    @Column(name = "domicilio_toma")
    private String domicilioToma;

    @Column(name = "fecha_instalacion")
    private LocalDate fechaInstalacion;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "titular_idtitular")
    private Titular titular;

    @ManyToOne
    @JoinColumn(name = "sector_idsector")
    private Sector sector;

    @ManyToOne
    @JoinColumn(name = "categoria_idcategoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "estado_contrato_idestado_contrato")
    private EstadoContrato estadoContrato;

    @ManyToOne
    @JoinColumn(name = "usuarioInstalador_idusuario")
    private Usuario usuarioInstalador;

    public Contrato() {}

    public Long getIdcontrato() { return idcontrato; }
    public void setIdcontrato(Long idcontrato) { this.idcontrato = idcontrato; }

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

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Titular getTitular() { return titular; }
    public void setTitular(Titular titular) { this.titular = titular; }

    public Sector getSector() { return sector; }
    public void setSector(Sector sector) { this.sector = sector; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public EstadoContrato getEstadoContrato() { return estadoContrato; }
    public void setEstadoContrato(EstadoContrato estadoContrato) { this.estadoContrato = estadoContrato; }

    public Usuario getUsuarioInstalador() { return usuarioInstalador; }
    public void setUsuarioInstalador(Usuario usuarioInstalador) { this.usuarioInstalador = usuarioInstalador; }
}
