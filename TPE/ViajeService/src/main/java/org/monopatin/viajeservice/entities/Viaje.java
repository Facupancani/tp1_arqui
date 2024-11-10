package org.monopatin.viajeservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idViaje;

    @Column(nullable = false)
    private Long idCuenta;

    @Column(nullable = false)
    private Long idUsuario;

    @Column(nullable = false)
    private Long idMonopatin;

    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private Double kilometrosRecorridos = 0.0;
    private Boolean enCurso = true;
    private Double costo = 0.0;

    private Long idParadaInicio;
    private Long idParadaFin;

}
