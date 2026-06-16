package com.sigat.dto;

import java.time.LocalDateTime;

import com.sigat.model.Usuario;

// Lo que el backend devuelve — nunca incluye el password_hash
public class UsuarioResponseDTO {

    private Long idusuario;
    private String username;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private Integer activo;
    private LocalDateTime fechaAlta;
    private Long rolId;
    private String rolNombre;

    // Constructor que convierte una entidad Usuario a este DTO
    public UsuarioResponseDTO(Usuario usuario) {
        this.idusuario       = usuario.getIdusuario();
        this.username        = usuario.getUsername();
        this.nombres         = usuario.getNombres();
        this.apellidoPaterno = usuario.getApellidoPaterno();
        this.apellidoMaterno = usuario.getApellidoMaterno();
        this.correo          = usuario.getCorreo();
        this.activo          = usuario.getActivo();
        this.fechaAlta       = usuario.getFechaAlta();
        this.rolId           = usuario.getRol().getIdrol();
        this.rolNombre       = usuario.getRol().getNombre();
    }

    public Long getIdusuario() { return idusuario; }
    public String getUsername() { return username; }
    public String getNombres() { return nombres; }
    public String getApellidoPaterno() { return apellidoPaterno; }
    public String getApellidoMaterno() { return apellidoMaterno; }
    public String getCorreo() { return correo; }
    public Integer getActivo() { return activo; }
    public LocalDateTime getFechaAlta() { return fechaAlta; }
    public Long getRolId() { return rolId; }
    public String getRolNombre() { return rolNombre; }
}
