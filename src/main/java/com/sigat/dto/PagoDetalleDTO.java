package com.sigat.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.sigat.model.Recibo;
import com.sigat.model.Titular;

public class PagoDetalleDTO {

    private Long idpago;
    private String folio;
    private BigDecimal montoRecibido;
    private LocalDateTime fechaPago;
    private String observaciones;
    private String tipoPagoNombre;
    private String usuarioNombre;
    private String municipioNombre;
    private List<ReciboItemDTO> recibos;

    public static class ReciboItemDTO {

        private Long idrecibo;
        private Integer mes;
        private Integer anio;
        private BigDecimal monto;
        private String numeroContrato;
        private String titularNombre;
        private String domicilioToma;

        public ReciboItemDTO(Recibo r) {
            this.idrecibo = r.getIdrecibo();
            this.mes = r.getMes();
            this.anio = r.getAnio();
            this.monto = r.getMonto();
            if (r.getContrato() != null) {
                this.numeroContrato = r.getContrato().getNumeroContrato();
                this.domicilioToma = r.getContrato().getDomicilioToma();
                Titular t = r.getContrato().getTitular();
                if (t != null) {
                    this.titularNombre = (t.getNombres() + " " + t.getApellido1()
                            + (t.getApellido2() != null ? " " + t.getApellido2() : "")).trim();
                }
            }
        }

        public Long getIdrecibo() { return idrecibo; }
        public Integer getMes() { return mes; }
        public Integer getAnio() { return anio; }
        public BigDecimal getMonto() { return monto; }
        public String getNumeroContrato() { return numeroContrato; }
        public String getTitularNombre() { return titularNombre; }
        public String getDomicilioToma() { return domicilioToma; }
    }

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

    public String getTipoPagoNombre() { return tipoPagoNombre; }
    public void setTipoPagoNombre(String tipoPagoNombre) { this.tipoPagoNombre = tipoPagoNombre; }

    public String getUsuarioNombre() { return usuarioNombre; }
    public void setUsuarioNombre(String usuarioNombre) { this.usuarioNombre = usuarioNombre; }

    public String getMunicipioNombre() { return municipioNombre; }
    public void setMunicipioNombre(String municipioNombre) { this.municipioNombre = municipioNombre; }

    public List<ReciboItemDTO> getRecibos() { return recibos; }
    public void setRecibos(List<ReciboItemDTO> recibos) { this.recibos = recibos; }
}
