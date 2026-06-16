package com.sigat.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.sigat.model.Bitacora;
import com.sigat.model.Usuario;
import com.sigat.repository.BitacoraRepository;

@Service
public class BitacoraService {

    private final BitacoraRepository bitacoraRepository;

    public BitacoraService(BitacoraRepository bitacoraRepository) {
        this.bitacoraRepository = bitacoraRepository;
    }

    // Metodo central que llaman los demas servicios para registrar una accion
    // Ejemplo: bitacoraService.registrar("CREATE", "contrato", "{\"id\":1}", "192.168.1.1", usuario)
    public void registrar(String accion, String entidad, String detalle, String ip, Usuario usuario) {
        Bitacora entrada = new Bitacora();
        entrada.setAccion(accion);
        entrada.setEntidadAfectada(entidad);
        entrada.setDetalle(detalle);
        entrada.setIpOrigen(ip);
        entrada.setFechaHora(LocalDateTime.now());
        entrada.setUsuario(usuario);
        bitacoraRepository.save(entrada);
    }
}
