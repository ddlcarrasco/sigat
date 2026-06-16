package com.sigat.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sigat.dto.UsuarioRequestDTO;
import com.sigat.model.Rol;
import com.sigat.model.Usuario;
import com.sigat.repository.RolRepository;
import com.sigat.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          RolRepository rolRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> getByRolNombre(String rolNombre) {
        return usuarioRepository.findByRol_Nombre(rolNombre);
    }

    public Usuario getById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + id));
    }

    public Usuario crear(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("El username ya esta en uso: " + dto.getUsername());
        }

        Rol rol = rolRepository.findById(dto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + dto.getRolId()));

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        usuario.setNombres(dto.getNombres());
        usuario.setApellidoPaterno(dto.getApellidoPaterno());
        usuario.setApellidoMaterno(dto.getApellidoMaterno());
        usuario.setCorreo(dto.getCorreo());
        usuario.setRol(rol);
        usuario.setActivo(1);
        usuario.setFechaAlta(LocalDateTime.now());

        return usuarioRepository.save(usuario);
    }

    public Usuario actualizar(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = getById(id);

        Rol rol = rolRepository.findById(dto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + dto.getRolId()));

        usuario.setNombres(dto.getNombres());
        usuario.setApellidoPaterno(dto.getApellidoPaterno());
        usuario.setApellidoMaterno(dto.getApellidoMaterno());
        usuario.setCorreo(dto.getCorreo());
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }

    public void cambiarPassword(Long id, String nuevaPassword) {
        Usuario usuario = getById(id);
        usuario.setPasswordHash(passwordEncoder.encode(nuevaPassword));
        usuarioRepository.save(usuario);
    }

    // Baja logica — no se borra el registro, solo se desactiva
    public void desactivar(Long id) {
        Usuario usuario = getById(id);
        usuario.setActivo(0);
        usuarioRepository.save(usuario);
    }
}
