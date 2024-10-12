package springboot.app.dto;

import lombok.Data;

@Data
public class ReporteDto {

    private String nombreCarrera;
    private int anioInscripcion;
    private Long inscriptos;
    private Long egresados;

    public ReporteDto(String nombreCarrera, int anioInscripcion, Long inscriptos, Long egresados) {
        this.nombreCarrera = nombreCarrera;
        this.anioInscripcion = anioInscripcion;
        this.inscriptos = inscriptos;
        this.egresados = egresados;
    }

}

