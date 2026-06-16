package com.sigat.dto;

// Lo que el frontend manda al crear o actualizar un usuario
public class UsuarioRequestDTO {

    private String username;

    // Password en texto plano — el servicio lo hashea antes de guardar
    private String password;

    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;

    // Solo el ID del rol, no el objeto completo
    private Long rolId;

    public UsuarioRequestDTO() {}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }

    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public Long getRolId() { return rolId; }
    public void setRolId(Long rolId) { this.rolId = rolId; }
}
