package com.sigat.dto;

import java.math.BigDecimal;
import java.util.List;

public class PagoRequestDTO {

    private BigDecimal montoRecibido;
    private String observaciones;
    private Long tipoPagoId;
    private List<Long> reciboIds;

    public PagoRequestDTO() {}

    public BigDecimal getMontoRecibido() { return montoRecibido; }
    public void setMontoRecibido(BigDecimal montoRecibido) { this.montoRecibido = montoRecibido; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public Long getTipoPagoId() { return tipoPagoId; }
    public void setTipoPagoId(Long tipoPagoId) { this.tipoPagoId = tipoPagoId; }

    public List<Long> getReciboIds() { return reciboIds; }
    public void setReciboIds(List<Long> reciboIds) { this.reciboIds = reciboIds; }
}
