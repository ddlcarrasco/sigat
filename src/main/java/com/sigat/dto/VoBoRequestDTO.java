package com.sigat.dto;

public class VoBoRequestDTO {

    // 2 = VoBo Aprobado, 3 = Rechazado
    private Long idEstadoNuevo;
    private String observaciones;

    public VoBoRequestDTO() {}

    public Long getIdEstadoNuevo() { return idEstadoNuevo; }
    public void setIdEstadoNuevo(Long idEstadoNuevo) { this.idEstadoNuevo = idEstadoNuevo; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
