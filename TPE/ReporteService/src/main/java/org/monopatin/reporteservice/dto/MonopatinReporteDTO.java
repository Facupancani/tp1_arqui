package org.monopatin.reporteservice.dto;

import lombok.Data;

@Data
public class MonopatinReporteDTO {

    private Long idMonopatin;
    private Double kilometraje;
    private Long tiempoDeUso;

    public MonopatinReporteDTO(){
    }

    public MonopatinReporteDTO(Long idMonopatin, Double kilometraje, Long tiempoDeUso) {
        this.idMonopatin = idMonopatin;
        this.kilometraje = kilometraje;
        this.tiempoDeUso = tiempoDeUso;
    }

}


