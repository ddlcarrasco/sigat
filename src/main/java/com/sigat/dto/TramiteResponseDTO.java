package com.sigat.dto;

import java.time.LocalDateTime;

import com.sigat.model.Tramite;

public class TramiteResponseDTO {

    private Long idtramite;
    private String datosPropuestos;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaResolucion;
    private String observacionesSolicitud;
    private String observacionesResolucion;

    private Long contratoId;
    private String contratoNumero;

    private Long tipoTramiteId;
    private String tipoTramiteNombre;

    private Long estadoTramiteId;
    private String estadoTramiteNombre;

    private Long usuarioSolicitanteId;
    private String usuarioSolicitanteNombre;

    private Long usuarioResolutorId;
    private String usuarioResolutorNombre;

    public TramiteResponseDTO(Tramite tramite) {
        this.idtramite = tramite.getIdtramite();
        this.datosPropuestos = tramite.getDatosPropuestos();
        this.fechaSolicitud = tramite.getFechaSolicitud();
        this.fechaResolucion = tramite.getFechaResolucion();
        this.observacionesSolicitud = tramite.getObservacionesSolicitud();
        this.observacionesResolucion = tramite.getObservacionesResolucion();

        if (tramite.getContrato() != null) {
            this.contratoId = tramite.getContrato().getIdcontrato();
            this.contratoNumero = tramite.getContrato().getNumeroContrato();
        }

        if (tramite.getTipoTramite() != null) {
            this.tipoTramiteId = tramite.getTipoTramite().getIdtipo_tramite();
            this.tipoTramiteNombre = tramite.getTipoTramite().getNombre();
        }

        if (tramite.getEstadoTramite() != null) {
            this.estadoTramiteId = tramite.getEstadoTramite().getIdestado_tramite();
            this.estadoTramiteNombre = tramite.getEstadoTramite().getNombre();
        }

        if (tramite.getUsuarioSolicitante() != null) {
            this.usuarioSolicitanteId = tramite.getUsuarioSolicitante().getIdusuario();
            this.usuarioSolicitanteNombre = tramite.getUsuarioSolicitante().getNombres()
                    + " " + tramite.getUsuarioSolicitante().getApellidoPaterno();
        }

        if (tramite.getUsuarioResolutor() != null) {
            this.usuarioResolutorId = tramite.getUsuarioResolutor().getIdusuario();
            this.usuarioResolutorNombre = tramite.getUsuarioResolutor().getNombres()
                    + " " + tramite.getUsuarioResolutor().getApellidoPaterno();
        }
    }

    public Long getIdtramite() { return idtramite; }
    public String getDatosPropuestos() { return datosPropuestos; }
    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public LocalDateTime getFechaResolucion() { return fechaResolucion; }
    public String getObservacionesSolicitud() { return observacionesSolicitud; }
    public String getObservacionesResolucion() { return observacionesResolucion; }
    public Long getContratoId() { return contratoId; }
    public String getContratoNumero() { return contratoNumero; }
    public Long getTipoTramiteId() { return tipoTramiteId; }
    public String getTipoTramiteNombre() { return tipoTramiteNombre; }
    public Long getEstadoTramiteId() { return estadoTramiteId; }
    public String getEstadoTramiteNombre() { return estadoTramiteNombre; }
    public Long getUsuarioSolicitanteId() { return usuarioSolicitanteId; }
    public String getUsuarioSolicitanteNombre() { return usuarioSolicitanteNombre; }
    public Long getUsuarioResolutorId() { return usuarioResolutorId; }
    public String getUsuarioResolutorNombre() { return usuarioResolutorNombre; }
}
