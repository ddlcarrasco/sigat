package com.sigat.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_descuento")
public class TipoDescuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtipo_descuento")
    private Long idtipo_descuento;

    @Column(name = "nombre")
    private String nombre;

    // Porcentaje de descuento, ej: 25.00 significa 25%
    @Column(name = "porcentaje")
    private BigDecimal porcentaje;

    @Column(name = "descripcion")
    private String descripcion;

    // 1 = activo, 0 = inactivo
    @Column(name = "activo", columnDefinition = "tinyint")
    private Integer activo;

    public TipoDescuento() {}

    public Long getIdtipo_descuento() { return idtipo_descuento; }
    public void setIdtipo_descuento(Long idtipo_descuento) { this.idtipo_descuento = idtipo_descuento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public BigDecimal getPorcentaje() { return porcentaje; }
    public void setPorcentaje(BigDecimal porcentaje) { this.porcentaje = porcentaje; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getActivo() { return activo; }
    public void setActivo(Integer activo) { this.activo = activo; }
}
