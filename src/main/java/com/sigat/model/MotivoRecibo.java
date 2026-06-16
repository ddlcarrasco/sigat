package com.sigat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "motivo_recibo")
public class MotivoRecibo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmotivo_recibo")
    private Integer idmotivo_recibo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    public MotivoRecibo() {}

    public Integer getIdmotivo_recibo() { return idmotivo_recibo; }
    public void setIdmotivo_recibo(Integer idmotivo_recibo) { this.idmotivo_recibo = idmotivo_recibo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
