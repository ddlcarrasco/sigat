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
@Table(name = "bitacora")
public class Bitacora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbitacora")
    private Long idbitacora;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Column(name = "accion")
    private String accion;

    @Column(name = "entidad_afectada")
    private String entidadAfectada;

    @Column(name = "detalle", columnDefinition = "json")
    private String detalle;

    @Column(name = "ip_origen")
    private String ipOrigen;

    @ManyToOne
    @JoinColumn(name = "usuario_idusuario")
    private Usuario usuario;

    public Bitacora() {}

    public Long getIdbitacora() { return idbitacora; }
    public void setIdbitacora(Long idbitacora) { this.idbitacora = idbitacora; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getAccion() { return accion; }
    public void setAccion(String accion) { this.accion = accion; }

    public String getEntidadAfectada() { return entidadAfectada; }
    public void setEntidadAfectada(String entidadAfectada) { this.entidadAfectada = entidadAfectada; }

    public String getDetalle() { return detalle; }
    public void setDetalle(String detalle) { this.detalle = detalle; }

    public String getIpOrigen() { return ipOrigen; }
    public void setIpOrigen(String ipOrigen) { this.ipOrigen = ipOrigen; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
