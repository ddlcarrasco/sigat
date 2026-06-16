package com.sigat.dto;

import java.math.BigDecimal;
import com.sigat.model.ContratoDescuento;

public class ContratoDescuentoResponseDTO {

    private Long idcontrato_descuento;
    private Long tipoDescuentoId;
    private String tipoDescuentoNombre;
    private BigDecimal porcentaje;
    private String descripcion;

    public ContratoDescuentoResponseDTO(ContratoDescuento cd) {
        this.idcontrato_descuento  = cd.getIdcontrato_descuento();
        this.tipoDescuentoId       = cd.getTipoDescuento().getIdtipo_descuento();
        this.tipoDescuentoNombre   = cd.getTipoDescuento().getNombre();
        this.porcentaje            = cd.getTipoDescuento().getPorcentaje();
        this.descripcion           = cd.getTipoDescuento().getDescripcion();
    }

    public Long getIdcontrato_descuento()  { return idcontrato_descuento; }
    public Long getTipoDescuentoId()       { return tipoDescuentoId; }
    public String getTipoDescuentoNombre() { return tipoDescuentoNombre; }
    public BigDecimal getPorcentaje()      { return porcentaje; }
    public String getDescripcion()         { return descripcion; }
}
