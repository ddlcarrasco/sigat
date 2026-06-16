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
@Table(name = "tramite")
public class Tramite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtramite")
    private Long idtramite;

    @Column(name = "datos_propuestos", columnDefinition = "json")
    private String datosPropuestos;

    @Column(name = "fecha_solicitud")
    private LocalDateTime fechaSolicitud;

    @Column(name = "fecha_resolucion")
    private LocalDateTime fechaResolucion;

    @Column(name = "observaciones_solicitud")
    private String observacionesSolicitud;

    @Column(name = "observaciones_resolucion")
    private String observacionesResolucion;

    @ManyToOne
    @JoinColumn(name = "tipo_tramite_idtipo_tramite")
    private TipoTramite tipoTramite;

    @ManyToOne
    @JoinColumn(name = "contrato_idcontrato")
    private Contrato contrato;

    @ManyToOne
    @JoinColumn(name = "estado_tramite_idestado_tramite")
    private EstadoTramite estadoTramite;

    @ManyToOne
    @JoinColumn(name = "usuarioSolicitante_idusuario")
    private Usuario usuarioSolicitante;

    @ManyToOne
    @JoinColumn(name = "usuarioResolutor_idusuario")
    private Usuario usuarioResolutor;

    public Tramite() {}

    public Long getIdtramite() { return idtramite; }
    public void setIdtramite(Long idtramite) { this.idtramite = idtramite; }

    public String getDatosPropuestos() { return datosPropuestos; }
    public void setDatosPropuestos(String datosPropuestos) { this.datosPropuestos = datosPropuestos; }

    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public LocalDateTime getFechaResolucion() { return fechaResolucion; }
    public void setFechaResolucion(LocalDateTime fechaResolucion) { this.fechaResolucion = fechaResolucion; }

    public String getObservacionesSolicitud() { return observacionesSolicitud; }
    public void setObservacionesSolicitud(String observacionesSolicitud) { this.observacionesSolicitud = observacionesSolicitud; }

    public String getObservacionesResolucion() { return observacionesResolucion; }
    public void setObservacionesResolucion(String observacionesResolucion) { this.observacionesResolucion = observacionesResolucion; }

    public TipoTramite getTipoTramite() { return tipoTramite; }
    public void setTipoTramite(TipoTramite tipoTramite) { this.tipoTramite = tipoTramite; }

    public Contrato getContrato() { return contrato; }
    public void setContrato(Contrato contrato) { this.contrato = contrato; }

    public EstadoTramite getEstadoTramite() { return estadoTramite; }
    public void setEstadoTramite(EstadoTramite estadoTramite) { this.estadoTramite = estadoTramite; }

    public Usuario getUsuarioSolicitante() { return usuarioSolicitante; }
    public void setUsuarioSolicitante(Usuario usuarioSolicitante) { this.usuarioSolicitante = usuarioSolicitante; }

    public Usuario getUsuarioResolutor() { return usuarioResolutor; }
    public void setUsuarioResolutor(Usuario usuarioResolutor) { this.usuarioResolutor = usuarioResolutor; }
}
