package org.monopatin.adminservice.dto;

import lombok.Data;

@Data
public class MonopatinDTO {

    private Long idMonopatin;
    private String modelo;
    private Double kilometraje;
    private Long tiempoDeUso;
    private Long tiempoEnPausa;
    private String estado;
    private Boolean enMantenimiento;

}
