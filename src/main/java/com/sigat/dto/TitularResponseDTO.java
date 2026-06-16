package com.sigat.dto;

import java.time.LocalDateTime;

import com.sigat.model.Titular;

public class TitularResponseDTO {

    private Long idtitular;
    private String curp;
    private String nombres;
    private String apellido1;
    private String apellido2;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String telefono;
    private String correo;
    private LocalDateTime fechaAlta;

    public TitularResponseDTO(Titular titular) {
        this.idtitular = titular.getIdtitular();
        this.curp = titular.getCurp();
        this.nombres = titular.getNombres();
        this.apellido1 = titular.getApellido1();
        this.apellido2 = titular.getApellido2();
        this.tipoIdentificacion = titular.getTipoIdentificacion();
        this.numeroIdentificacion = titular.getNumeroIdentificacion();
        this.telefono = titular.getTelefono();
        this.correo = titular.getCorreo();
        this.fechaAlta = titular.getFechaAlta();
    }

    public Long getIdtitular() { return idtitular; }
    public String getCurp() { return curp; }
    public String getNombres() { return nombres; }
    public String getApellido1() { return apellido1; }
    public String getApellido2() { return apellido2; }
    public String getTipoIdentificacion() { return tipoIdentificacion; }
    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }
    public LocalDateTime getFechaAlta() { return fechaAlta; }
}
