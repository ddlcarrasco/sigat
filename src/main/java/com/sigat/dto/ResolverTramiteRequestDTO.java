package com.sigat.dto;

public class ResolverTramiteRequestDTO {

    private Long idEstadoNuevo;
    private String observacionesResolucion;

    public ResolverTramiteRequestDTO() {}

    public Long getIdEstadoNuevo() { return idEstadoNuevo; }
    public void setIdEstadoNuevo(Long idEstadoNuevo) { this.idEstadoNuevo = idEstadoNuevo; }

    public String getObservacionesResolucion() { return observacionesResolucion; }
    public void setObservacionesResolucion(String observacionesResolucion) { this.observacionesResolucion = observacionesResolucion; }
}
