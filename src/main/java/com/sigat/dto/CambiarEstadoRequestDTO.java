package com.sigat.dto;

public class CambiarEstadoRequestDTO {

    private Long idEstadoNuevo;
    private String observaciones;
    private Long idUsuarioResponsable;

    public CambiarEstadoRequestDTO() {}

    public Long getIdEstadoNuevo() { return idEstadoNuevo; }
    public void setIdEstadoNuevo(Long idEstadoNuevo) { this.idEstadoNuevo = idEstadoNuevo; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public Long getIdUsuarioResponsable() { return idUsuarioResponsable; }
    public void setIdUsuarioResponsable(Long idUsuarioResponsable) { this.idUsuarioResponsable = idUsuarioResponsable; }
}
