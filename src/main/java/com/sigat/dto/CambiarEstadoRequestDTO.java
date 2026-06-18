package com.sigat.dto;

public class CambiarEstadoRequestDTO {

    private Long idEstadoNuevo;
    private String observaciones;

    public CambiarEstadoRequestDTO() {}

    public Long getIdEstadoNuevo() { return idEstadoNuevo; }
    public void setIdEstadoNuevo(Long idEstadoNuevo) { this.idEstadoNuevo = idEstadoNuevo; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
