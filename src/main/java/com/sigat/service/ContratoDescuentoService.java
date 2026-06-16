package com.sigat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sigat.model.Contrato;
import com.sigat.model.ContratoDescuento;
import com.sigat.model.TipoDescuento;
import com.sigat.repository.ContratoDescuentoRepository;
import com.sigat.repository.ContratoRepository;
import com.sigat.repository.TipoDescuentoRepository;

@Service
public class ContratoDescuentoService {

    private final ContratoDescuentoRepository contratoDescuentoRepository;
    private final ContratoRepository contratoRepository;
    private final TipoDescuentoRepository tipoDescuentoRepository;

    public ContratoDescuentoService(ContratoDescuentoRepository contratoDescuentoRepository,
                                    ContratoRepository contratoRepository,
                                    TipoDescuentoRepository tipoDescuentoRepository) {
        this.contratoDescuentoRepository = contratoDescuentoRepository;
        this.contratoRepository          = contratoRepository;
        this.tipoDescuentoRepository     = tipoDescuentoRepository;
    }

    public List<ContratoDescuento> getByContrato(Long idContrato) {
        Contrato contrato = contratoRepository.findById(idContrato)
                .orElseThrow(() -> new RuntimeException("Contrato no encontrado: " + idContrato));
        return contratoDescuentoRepository.findByContrato(contrato);
    }

    public ContratoDescuento asignar(Long idContrato, Long idTipoDescuento) {
        Contrato contrato = contratoRepository.findById(idContrato)
                .orElseThrow(() -> new RuntimeException("Contrato no encontrado: " + idContrato));
        TipoDescuento tipoDescuento = tipoDescuentoRepository.findById(idTipoDescuento)
                .orElseThrow(() -> new RuntimeException("Tipo de descuento no encontrado: " + idTipoDescuento));

        boolean yaAsignado = contratoDescuentoRepository.findByContrato(contrato).stream()
                .anyMatch(cd -> cd.getTipoDescuento().getIdtipo_descuento().equals(idTipoDescuento));
        if (yaAsignado) {
            throw new RuntimeException("Este descuento ya está asignado a este contrato");
        }

        ContratoDescuento cd = new ContratoDescuento();
        cd.setContrato(contrato);
        cd.setTipoDescuento(tipoDescuento);
        return contratoDescuentoRepository.save(cd);
    }

    public void quitar(Long idContratoDescuento) {
        if (!contratoDescuentoRepository.existsById(idContratoDescuento)) {
            throw new RuntimeException("Asignación de descuento no encontrada: " + idContratoDescuento);
        }
        contratoDescuentoRepository.deleteById(idContratoDescuento);
    }
}
