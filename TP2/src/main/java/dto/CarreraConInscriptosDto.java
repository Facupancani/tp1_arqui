package dto;

public class CarreraConInscriptosDto {

    private int idCarrera;
    private String nombreCarrera;
    private int inscriptos;

    public CarreraConInscriptosDto(int idCarrera, String nombreCarrera, int inscriptos) {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
        this.inscriptos = inscriptos;
    }

    @Override
    public String toString() {
        return "CarreraConInscriptosDto{" +
                "idCarrera=" + idCarrera +
                ", nombreCarrera='" + nombreCarrera + '\'' +
                ", inscriptos=" + inscriptos +
                '}';
    }

}
