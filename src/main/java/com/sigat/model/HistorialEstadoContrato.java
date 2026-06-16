package com.sigat.model;

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
@Table(name = "historial_estado_contrato")
public class HistorialEstadoContrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhistorial_estado_contrato")
    private Long idhistorial_estado_contrato;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "observaciones")
    private String observaciones;

    // Contrato que cambio de estado
    @ManyToOne
    @JoinColumn(name = "contrato_idcontrato")
    private Contrato contrato;

    // Estado en el que estaba el contrato antes del cambio
    @ManyToOne
    @JoinColumn(name = "estado_contratoAnterior_idestado_contrato")
    private EstadoContrato estadoAnterior;

    // Estado al que paso el contrato despues del cambio
    @ManyToOne
    @JoinColumn(name = "estado_contratoNuevo_idestado_contrato")
    private EstadoContrato estadoNuevo;

    // Usuario que realizo el cambio de estado
    @ManyToOne
    @JoinColumn(name = "usuario_idusuario")
    private Usuario usuario;

    public HistorialEstadoContrato() {}

    public Long getIdhistorial_estado_contrato() { return idhistorial_estado_contrato; }
    public void setIdhistorial_estado_contrato(Long idhistorial_estado_contrato) { this.idhistorial_estado_contrato = idhistorial_estado_contrato; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public Contrato getContrato() { return contrato; }
    public void setContrato(Contrato contrato) { this.contrato = contrato; }

    public EstadoContrato getEstadoAnterior() { return estadoAnterior; }
    public void setEstadoAnterior(EstadoContrato estadoAnterior) { this.estadoAnterior = estadoAnterior; }

    public EstadoContrato getEstadoNuevo() { return estadoNuevo; }
    public void setEstadoNuevo(EstadoContrato estadoNuevo) { this.estadoNuevo = estadoNuevo; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
