package com.sigat.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.sigat.dto.PagoRequestDTO;
import com.sigat.model.EstadoRecibo;
import com.sigat.model.Pago;
import com.sigat.model.Recibo;
import com.sigat.repository.EstadoReciboRepository;
import com.sigat.repository.PagoRepository;
import com.sigat.repository.ReciboRepository;
import com.sigat.repository.TipoPagoRepository;
import com.sigat.repository.UsuarioRepository;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;
    private final ReciboRepository reciboRepository;
    private final EstadoReciboRepository estadoReciboRepository;
    private final TipoPagoRepository tipoPagoRepository;
    private final UsuarioRepository usuarioRepository;

    public PagoService(PagoRepository pagoRepository,
                       ReciboRepository reciboRepository,
                       EstadoReciboRepository estadoReciboRepository,
                       TipoPagoRepository tipoPagoRepository,
                       UsuarioRepository usuarioRepository) {
        this.pagoRepository = pagoRepository;
        this.reciboRepository = reciboRepository;
        this.estadoReciboRepository = estadoReciboRepository;
        this.tipoPagoRepository = tipoPagoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public java.util.List<Pago> getAll() {
        return pagoRepository.findAll();
    }

    public Pago getById(Long id) {
        return pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado: " + id));
    }

    public Pago getByFolio(String folio) {
        return pagoRepository.findByFolio(folio)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con folio: " + folio));
    }

    public Pago registrarPago(PagoRequestDTO dto, String username) {
        if (pagoRepository.existsByFolio(dto.getFolio())) {
            throw new RuntimeException("Ya existe un pago con el folio: " + dto.getFolio());
        }
        if (dto.getReciboIds() == null || dto.getReciboIds().isEmpty()) {
            throw new RuntimeException("Debe indicar al menos un recibo a liquidar");
        }

        Pago pago = new Pago();
        pago.setFolio(dto.getFolio());
        pago.setMontoRecibido(dto.getMontoRecibido());
        pago.setObservaciones(dto.getObservaciones());
        pago.setFechaPago(LocalDateTime.now());
        pago.setTipoPago(tipoPagoRepository.findById(dto.getTipoPagoId())
                .orElseThrow(() -> new RuntimeException("Tipo de pago no encontrado: " + dto.getTipoPagoId())));
        pago.setUsuario(usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username)));

        Pago pagoGuardado = pagoRepository.save(pago);

        // Estado 2 = Pagado
        EstadoRecibo estadoPagado = estadoReciboRepository.findById(2)
                .orElseThrow(() -> new RuntimeException("Estado 'Pagado' no encontrado"));

        for (Long idRecibo : dto.getReciboIds()) {
            Recibo recibo = reciboRepository.findById(idRecibo).orElseThrow(() -> new RuntimeException("Recibo no encontrado: " + idRecibo));
            recibo.setPago(pagoGuardado);
            recibo.setEstadoRecibo(estadoPagado);
            reciboRepository.save(recibo);
        }

        return pagoGuardado;
    }
}
