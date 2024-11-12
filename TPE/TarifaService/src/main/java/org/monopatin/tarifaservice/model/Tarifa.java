package org.monopatin.tarifaservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarifa;

    @Column(nullable = false)
    private Double tarifaPorMinuto;
    @Column(nullable = false)
    private Double tarifaExtra;

    @Column(nullable = false)
    private LocalDate fechaInicioVigencia;
    @Column
    private LocalDate fechaFinVigencia;  // Puede ser null si no hay fecha de fin definida

    public Tarifa() {
    }

    public Tarifa(Double tarifaPorMinuto, Double tarifaExtra, LocalDate fechaInicioVigencia) {
        this.tarifaPorMinuto = tarifaPorMinuto;
        this.tarifaExtra = tarifaExtra;
        this.fechaInicioVigencia = fechaInicioVigencia;
    }
}
