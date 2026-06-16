package com.sigat.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.sigat.model.Pago;

public class PagoResponseDTO {

    private Long idpago;
    private String folio;
    private BigDecimal montoRecibido;
    private LocalDateTime fechaPago;
    private String observaciones;

    private Long tipoPagoId;
    private String tipoPagoNombre;

    private Long usuarioId;
    private String usuarioNombre;

    public PagoResponseDTO(Pago pago) {
        this.idpago = pago.getIdpago();
        this.folio = pago.getFolio();
        this.montoRecibido = pago.getMontoRecibido();
        this.fechaPago = pago.getFechaPago();
        this.observaciones = pago.getObservaciones();

        if (pago.getTipoPago() != null) {
            this.tipoPagoId = pago.getTipoPago().getIdtipo_pago();
            this.tipoPagoNombre = pago.getTipoPago().getNombre();
        }

        if (pago.getUsuario() != null) {
            this.usuarioId = pago.getUsuario().getIdusuario();
            this.usuarioNombre = pago.getUsuario().getNombres()
                    + " " + pago.getUsuario().getApellidoPaterno();
        }
    }

    public Long getIdpago() { return idpago; }
    public String getFolio() { return folio; }
    public BigDecimal getMontoRecibido() { return montoRecibido; }
    public LocalDateTime getFechaPago() { return fechaPago; }
    public String getObservaciones() { return observaciones; }
    public Long getTipoPagoId() { return tipoPagoId; }
    public String getTipoPagoNombre() { return tipoPagoNombre; }
    public Long getUsuarioId() { return usuarioId; }
    public String getUsuarioNombre() { return usuarioNombre; }
}
