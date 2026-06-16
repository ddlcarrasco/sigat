package com.sigat.dto;

public class TramiteRequestDTO {

    private Long contratoId;
    private Long tipoTramiteId;
    private String datosPropuestos;
    private String observacionesSolicitud;

    public TramiteRequestDTO() {}

    public Long getContratoId() { return contratoId; }
    public void setContratoId(Long contratoId) { this.contratoId = contratoId; }

    public Long getTipoTramiteId() { return tipoTramiteId; }
    public void setTipoTramiteId(Long tipoTramiteId) { this.tipoTramiteId = tipoTramiteId; }

    public String getDatosPropuestos() { return datosPropuestos; }
    public void setDatosPropuestos(String datosPropuestos) { this.datosPropuestos = datosPropuestos; }

    public String getObservacionesSolicitud() { return observacionesSolicitud; }
    public void setObservacionesSolicitud(String observacionesSolicitud) { this.observacionesSolicitud = observacionesSolicitud; }
}
