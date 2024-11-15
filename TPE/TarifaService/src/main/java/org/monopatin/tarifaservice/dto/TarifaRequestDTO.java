package org.monopatin.tarifaservice.dto;

import java.time.LocalDate;

public class TarifaRequestDTO {

    private double tarifaPorMinuto;
    private double tarifaExtra;
    private LocalDate fechaInicioVigencia;

    // Getters y Setters
    public double getTarifaPorMinuto() {
        return tarifaPorMinuto;
    }

    public void setTarifaPorMinuto(double tarifaPorMinuto) {
        this.tarifaPorMinuto = tarifaPorMinuto;
    }

    public double getTarifaExtra() {
        return tarifaExtra;
    }

    public void setTarifaExtra(double tarifaExtra) {
        this.tarifaExtra = tarifaExtra;
    }

    public LocalDate getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(LocalDate fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }
}
