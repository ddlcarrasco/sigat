package com.sigat.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sigat.dto.ReciboRequestDTO;
import com.sigat.model.Contrato;
import com.sigat.model.ContratoDescuento;
import com.sigat.model.EstadoRecibo;
import com.sigat.model.MotivoRecibo;
import com.sigat.model.Recibo;
import com.sigat.model.Tarifa;
import com.sigat.repository.ContratoDescuentoRepository;
import com.sigat.repository.ContratoRepository;
import com.sigat.repository.EstadoReciboRepository;
import com.sigat.repository.MotivoReciboRepository;
import com.sigat.repository.ReciboRepository;

@Service
public class ReciboService {

    private final ReciboRepository reciboRepository;
    private final TarifaService tarifaService;
    private final ContratoDescuentoRepository contratoDescuentoRepository;
    private final EstadoReciboRepository estadoReciboRepository;
    private final ContratoRepository contratoRepository;
    private final MotivoReciboRepository motivoReciboRepository;

    public ReciboService(ReciboRepository reciboRepository,
                         TarifaService tarifaService,
                         ContratoDescuentoRepository contratoDescuentoRepository,
                         EstadoReciboRepository estadoReciboRepository,
                         ContratoRepository contratoRepository,
                         MotivoReciboRepository motivoReciboRepository) {
        this.reciboRepository = reciboRepository;
        this.tarifaService = tarifaService;
        this.contratoDescuentoRepository = contratoDescuentoRepository;
        this.estadoReciboRepository = estadoReciboRepository;
        this.contratoRepository = contratoRepository;
        this.motivoReciboRepository = motivoReciboRepository;
    }

    public List<Recibo> getAll() {
        return reciboRepository.findAll();
    }

    public Recibo getById(Long id) {
        return reciboRepository.findById(id).orElseThrow(()-> new RuntimeException("Recibo no encontrado: " + id));
    }

    public List<Recibo> getByContrato(Contrato contrato) {
        return reciboRepository.findByContrato(contrato);
    }

    public List<Recibo> getByContratoId(Long idContrato) {
        Contrato contrato = contratoRepository.findById(idContrato).orElseThrow(()-> new RuntimeException("Contrato no encontrado: " + idContrato));
        return reciboRepository.findByContrato(contrato);
    }

    public List<Recibo> getPendientesByContratoId(Long idContrato) {
        Contrato contrato = contratoRepository.findById(idContrato).orElseThrow(()-> new RuntimeException("Contrato no encontrado: " + idContrato));
        EstadoRecibo estadoPendiente = estadoReciboRepository.findById(1).orElseThrow(()-> new RuntimeException("Estado 'Pendiente' no encontrado"));
        return reciboRepository.findByContratoAndEstadoRecibo(contrato, estadoPendiente);
    }

    public List<Recibo> getPendientesByContrato(Contrato contrato) {
        EstadoRecibo estadoPendiente = estadoReciboRepository.findById(1).orElseThrow(()-> new RuntimeException("Estado 'Pendiente' no encontrado"));
        return reciboRepository.findByContratoAndEstadoRecibo(contrato, estadoPendiente);
    }

    public Recibo generarReciboMensual(ReciboRequestDTO dto) {
        Contrato contrato = contratoRepository.findById(dto.getContratoId()).orElseThrow(()-> new RuntimeException("Contrato no encontrado: " + dto.getContratoId()));
        MotivoRecibo motivo = motivoReciboRepository.findById(dto.getMotivoReciboId()).orElseThrow(()-> new RuntimeException("Motivo de recibo no encontrado: " + dto.getMotivoReciboId()));
        int mes=dto.getMes();
        int anio=dto.getAnio();

        if (reciboRepository.existsByContratoAndMesAndAnio(contrato, mes, anio)) {
            throw new RuntimeException("Ya existe un recibo para el contrato "+ contrato.getNumeroContrato() + " en " + dto.getMes() + "/" + dto.getAnio());
        }
        Tarifa tarifa = tarifaService.getTarifaVigente(contrato.getSector(), contrato.getCategoria());
        BigDecimal monto = calcularMonto(contrato, tarifa.getMontoMensual());

        Recibo recibo = new Recibo();
        recibo.setContrato(contrato);
        recibo.setMes(mes);
        recibo.setAnio(anio);
        recibo.setMonto(monto);
        recibo.setFechaEmision(LocalDate.now());
        recibo.setFechaVencimiento(dto.getFechaVencimiento());
        recibo.setTarifa(tarifa);
        recibo.setMotivoRecibo(motivo);
        recibo.setEstadoRecibo(estadoReciboRepository.findById(1).orElseThrow(()-> new RuntimeException("Estado 'Pendiente' no encontrado")));
        recibo.setPago(null);

        return reciboRepository.save(recibo);
    }

    // TODO: PENDIENTE DE REVISION — La regla de negocio para combinar multiples descuentos
    // aun no esta definida. Actualmente suma los porcentajes (50% + 25% = 75% de descuento),
    // pero podria ser que se apliquen de forma escalonada u otro criterio.
    // Confirmar con el municipio antes de usar en produccion.
    private BigDecimal calcularMonto(Contrato contrato, BigDecimal montoBase) {
        List<ContratoDescuento> descuentos = contratoDescuentoRepository.findByContrato(contrato);

        BigDecimal totalDescuento = descuentos.stream()
                .map(cd -> cd.getTipoDescuento().getPorcentaje())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (totalDescuento.compareTo(new BigDecimal("100")) > 0) {
            totalDescuento = new BigDecimal("100");
        }

        BigDecimal factor = BigDecimal.ONE.subtract(
                totalDescuento.divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP));

        return montoBase.multiply(factor).setScale(2, RoundingMode.HALF_UP);
    }
}
