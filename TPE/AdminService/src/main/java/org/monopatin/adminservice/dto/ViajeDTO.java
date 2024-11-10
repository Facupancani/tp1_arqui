package org.monopatin.adminservice.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ViajeDTO {

    private Long idViaje;
    private Long idCuenta;
    private Long idUsuario;
    private Long idMonopatin;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private LocalDateTime fechaHoraTiempoPausaMaximoExcedido;
    private Double kilometrosRecorridos;
    private Boolean enCurso;
    private Double costo;
    private Long idParadaInicio;
    private Long idParadaFin;

}
