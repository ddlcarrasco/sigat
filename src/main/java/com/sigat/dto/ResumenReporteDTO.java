package com.sigat.dto;

import java.math.BigDecimal;

public class ResumenReporteDTO {

    private Long totalContratos;
    private Long contratosActivos;
    private Long recibosPendientes;
    private Long tramitesPendientes;
    private BigDecimal montoCobradoMesActual;
    private BigDecimal montoCobradoAnioActual;

    public ResumenReporteDTO() {}

    public Long getTotalContratos() { return totalContratos; }
    public void setTotalContratos(Long totalContratos) { this.totalContratos = totalContratos; }

    public Long getContratosActivos() { return contratosActivos; }
    public void setContratosActivos(Long contratosActivos) { this.contratosActivos = contratosActivos; }

    public Long getRecibosPendientes() { return recibosPendientes; }
    public void setRecibosPendientes(Long recibosPendientes) { this.recibosPendientes = recibosPendientes; }

    public Long getTramitesPendientes() { return tramitesPendientes; }
    public void setTramitesPendientes(Long tramitesPendientes) { this.tramitesPendientes = tramitesPendientes; }

    public BigDecimal getMontoCobradoMesActual() { return montoCobradoMesActual; }
    public void setMontoCobradoMesActual(BigDecimal montoCobradoMesActual) { this.montoCobradoMesActual = montoCobradoMesActual; }

    public BigDecimal getMontoCobradoAnioActual() { return montoCobradoAnioActual; }
    public void setMontoCobradoAnioActual(BigDecimal montoCobradoAnioActual) { this.montoCobradoAnioActual = montoCobradoAnioActual; }
}
