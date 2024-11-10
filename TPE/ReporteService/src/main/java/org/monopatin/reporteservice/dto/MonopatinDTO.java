package org.monopatin.reporteservice.dto;

import lombok.Data;

@Data
public class MonopatinDTO {

    private Long idMonopatin;
    private Double kilometraje;
    private Long tiempoDeUso;
    private Long tiempoEnPausa;

    public Long getTiempoConPausas(){
        return this.tiempoDeUso + this.tiempoEnPausa;
    }

    public Long getTiempoSinPausas(){
        return this.tiempoDeUso - this.tiempoEnPausa;
    }

}
