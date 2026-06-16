package com.sigat.model;

import java.math.BigDecimal;
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
@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpago")
    private Long idpago;

    @Column(name = "folio")
    private String folio;

    @Column(name = "monto_recibido")
    private BigDecimal montoRecibido;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    @Column(name = "observaciones")
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "tipo_pago_idtipo_pago")
    private TipoPago tipoPago;

    @ManyToOne
    @JoinColumn(name = "usuario_idusuario")
    private Usuario usuario;

    public Pago() {}

    public Long getIdpago() { return idpago; }
    public void setIdpago(Long idpago) { this.idpago = idpago; }

    public String getFolio() { return folio; }
    public void setFolio(String folio) { this.folio = folio; }

    public BigDecimal getMontoRecibido() { return montoRecibido; }
    public void setMontoRecibido(BigDecimal montoRecibido) { this.montoRecibido = montoRecibido; }

    public LocalDateTime getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDateTime fechaPago) { this.fechaPago = fechaPago; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public TipoPago getTipoPago() { return tipoPago; }
    public void setTipoPago(TipoPago tipoPago) { this.tipoPago = tipoPago; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
