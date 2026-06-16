package com.sigat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategoria")
    private Long idcategoria;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    // 1 = activa, 0 = inactiva
    @Column(name = "activo", columnDefinition = "tinyint")
    private Integer activo;

    public Categoria() {}

    public Long getIdcategoria() { return idcategoria; }
    public void setIdcategoria(Long idcategoria) { this.idcategoria = idcategoria; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getActivo() { return activo; }
    public void setActivo(Integer activo) { this.activo = activo; }
}
