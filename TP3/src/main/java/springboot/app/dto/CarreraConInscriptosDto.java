package springboot.app.dto;

import lombok.Data;

@Data
public class CarreraConInscriptosDto {

    private String carrera;
    private int inscriptos;

    public CarreraConInscriptosDto(String carrera, int inscriptos) {
        this.carrera = carrera;
        this.inscriptos = inscriptos;
    }

}
