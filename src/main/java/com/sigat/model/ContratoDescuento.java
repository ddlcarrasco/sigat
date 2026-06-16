package com.sigat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contrato_descuento")
public class ContratoDescuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcontrato_descuento")
    private Long idcontrato_descuento;

    // Contrato al que se le aplica el descuento
    @ManyToOne
    @JoinColumn(name = "contrato_idcontrato")
    private Contrato contrato;

    // Tipo de descuento que se le aplica al contrato
    @ManyToOne
    @JoinColumn(name = "tipo_descuento_idtipo_descuento")
    private TipoDescuento tipoDescuento;

    public ContratoDescuento() {}

    public Long getIdcontrato_descuento() { return idcontrato_descuento; }
    public void setIdcontrato_descuento(Long idcontrato_descuento) { this.idcontrato_descuento = idcontrato_descuento; }

    public Contrato getContrato() { return contrato; }
    public void setContrato(Contrato contrato) { this.contrato = contrato; }

    public TipoDescuento getTipoDescuento() { return tipoDescuento; }
    public void setTipoDescuento(TipoDescuento tipoDescuento) { this.tipoDescuento = tipoDescuento; }
}
