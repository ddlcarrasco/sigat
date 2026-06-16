package com.sigat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_tramite")
public class TipoTramite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtipo_tramite")
    private Long idtipo_tramite;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "requiere_vobo", columnDefinition = "tinyint")
    private Integer requiereVobo;

    public TipoTramite() {}

    public Long getIdtipo_tramite() { return idtipo_tramite; }
    public void setIdtipo_tramite(Long idtipo_tramite) { this.idtipo_tramite = idtipo_tramite; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getRequiereVobo() { return requiereVobo; }
    public void setRequiereVobo(Integer requiereVobo) { this.requiereVobo = requiereVobo; }
}
