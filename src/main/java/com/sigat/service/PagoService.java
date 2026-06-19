package com.sigat.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigat.dto.PagoDetalleDTO;
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

    @Value("${sigat.municipio.iniciales}")
    private String inicialesMunicipio;

    @Value("${sigat.municipio.nombre}")
    private String nombreMunicipio;

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

    public PagoDetalleDTO getDetalle(Long id) {
        Pago pago = getById(id);

        PagoDetalleDTO dto = new PagoDetalleDTO();
        dto.setIdpago(pago.getIdpago());
        dto.setFolio(pago.getFolio());
        dto.setMontoRecibido(pago.getMontoRecibido());
        dto.setFechaPago(pago.getFechaPago());
        dto.setObservaciones(pago.getObservaciones());
        dto.setMunicipioNombre(nombreMunicipio);

        if (pago.getTipoPago() != null)
            dto.setTipoPagoNombre(pago.getTipoPago().getNombre());

        if (pago.getUsuario() != null)
            dto.setUsuarioNombre(pago.getUsuario().getNombres()
                    + " " + pago.getUsuario().getApellidoPaterno());

        dto.setRecibos(
            reciboRepository.findByPago_Idpago(id).stream()
                .sorted(java.util.Comparator.comparing(r -> r.getAnio() * 100 + r.getMes()))
                .map(PagoDetalleDTO.ReciboItemDTO::new)
                .toList()
        );

        return dto;
    }

    @Transactional
    public Pago registrarPago(PagoRequestDTO dto, String username) {
        if (dto.getReciboIds() == null || dto.getReciboIds().isEmpty()) {
            throw new RuntimeException("Debe indicar al menos un recibo a liquidar");
        }

        Pago pago = new Pago();
        pago.setMontoRecibido(dto.getMontoRecibido());
        pago.setObservaciones(dto.getObservaciones());
        pago.setFechaPago(LocalDateTime.now());
        pago.setTipoPago(tipoPagoRepository.findById(dto.getTipoPagoId())
                .orElseThrow(() -> new RuntimeException("Tipo de pago no encontrado: " + dto.getTipoPagoId())));
        pago.setUsuario(usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username)));

        // Primer save: MySQL asigna el idpago
        Pago pagoGuardado = pagoRepository.saveAndFlush(pago);

        // Generar folio definitivo basado en el ID: MTY-REC000000001
        pagoGuardado.setFolio(inicialesMunicipio + "-REC" + String.format("%09d", pagoGuardado.getIdpago()));
        pagoGuardado = pagoRepository.save(pagoGuardado);

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
