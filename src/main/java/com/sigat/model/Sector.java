package com.sigat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sector")
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsector")
    private Long idsector;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    // 1 = activo, 0 = inactivo
    @Column(name = "activo", columnDefinition = "tinyint")
    private Integer activo;

    public Sector() {}

    public Long getIdsector() { return idsector; }
    public void setIdsector(Long idsector) { this.idsector = idsector; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getActivo() { return activo; }
    public void setActivo(Integer activo) { this.activo = activo; }
}
