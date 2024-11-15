package org.monopatin.monopatinservice.dto;

public class ReporteUsoMonopatinDTO {
    private Long idMonopatin;
    private double kilometrosRecorridos;
    private double tiempoDeUso;

    public ReporteUsoMonopatinDTO(Long idMonopatin, double kilometrosRecorridos, double tiempoDeUso) {
        this.idMonopatin = idMonopatin;
        this.kilometrosRecorridos = kilometrosRecorridos;
        this.tiempoDeUso = tiempoDeUso;
    }

    public Long getIdMonopatin() {
        return idMonopatin;
    }

    public void setIdMonopatin(Long idMonopatin) {
        this.idMonopatin = idMonopatin;
    }

    public double getKilometrosRecorridos() {
        return kilometrosRecorridos;
    }

    public void setKilometrosRecorridos(double kilometrosRecorridos) {
        this.kilometrosRecorridos = kilometrosRecorridos;
    }

    public double getTiempoDeUso() {
        return tiempoDeUso;
    }

    public void setTiempoDeUso(double tiempoDeUso) {
        this.tiempoDeUso = tiempoDeUso;
    }
}
