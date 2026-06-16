package com.sigat.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recibo")
public class Recibo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrecibo")
    private Long idrecibo;

    @Column(name = "mes", columnDefinition = "tinyint")
    private Integer mes;

    @Column(name = "anio", columnDefinition = "smallint")
    private Integer anio;

    @Column(name = "monto")
    private BigDecimal monto;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @ManyToOne
    @JoinColumn(name = "contrato_idcontrato")
    private Contrato contrato;

    @ManyToOne
    @JoinColumn(name = "tarifa_idtarifa")
    private Tarifa tarifa;

    @ManyToOne
    @JoinColumn(name = "motivo_recibo_idmotivo_recibo")
    private MotivoRecibo motivoRecibo;

    @ManyToOne
    @JoinColumn(name = "estado_recibo_idestado_recibo")
    private EstadoRecibo estadoRecibo;

    @ManyToOne
    @JoinColumn(name = "pago_idpago")
    private Pago pago;

    public Recibo() {}

    public Long getIdrecibo() { return idrecibo; }
    public void setIdrecibo(Long idrecibo) { this.idrecibo = idrecibo; }

    public Integer getMes() { return mes; }
    public void setMes(Integer mes) { this.mes = mes; }

    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }

    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public Contrato getContrato() { return contrato; }
    public void setContrato(Contrato contrato) { this.contrato = contrato; }

    public Tarifa getTarifa() { return tarifa; }
    public void setTarifa(Tarifa tarifa) { this.tarifa = tarifa; }

    public MotivoRecibo getMotivoRecibo() { return motivoRecibo; }
    public void setMotivoRecibo(MotivoRecibo motivoRecibo) { this.motivoRecibo = motivoRecibo; }

    public EstadoRecibo getEstadoRecibo() { return estadoRecibo; }
    public void setEstadoRecibo(EstadoRecibo estadoRecibo) { this.estadoRecibo = estadoRecibo; }

    public Pago getPago() { return pago; }
    public void setPago(Pago pago) { this.pago = pago; }
}
