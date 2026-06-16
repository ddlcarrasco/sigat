package com.sigat.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sigat.model.Rol;
import com.sigat.model.Usuario;
import com.sigat.repository.RolRepository;
import com.sigat.repository.UsuarioRepository;

@Configuration
public class DataInitializer {

    @Value("${admin.initial.password:#{null}}")
    private String adminInitialPassword;

    @Bean
    public CommandLineRunner crearAdminInicial(UsuarioRepository usuarioRepository,
                                               RolRepository rolRepository,
                                               PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.existsByUsername("admin")) {
                return;
            }
            if (adminInitialPassword == null || adminInitialPassword.isBlank()) {
                System.out.println(">>> AVISO: No se creo el usuario admin. Define la variable 'admin.initial.password' en application.properties o como variable de entorno.");
                return;
            }

            Rol rolAdmin = rolRepository.findByNombre("ADMIN")
                    .orElseThrow(() -> new RuntimeException(
                            "No se encontro el rol ADMIN en la base de datos. Verifique la tabla 'rol'."));

            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPasswordHash(passwordEncoder.encode(adminInitialPassword));
            admin.setNombres("Administrador");
            admin.setApellidoPaterno("Sistema");
            admin.setApellidoMaterno("");
            admin.setCorreo("admin@tlaxco.gob.mx");
            admin.setActivo(1);
            admin.setFechaAlta(LocalDateTime.now());
            admin.setRol(rolAdmin);

            usuarioRepository.save(admin);
            System.out.println(">>> Usuario admin creado correctamente.");
        };
    }
}
