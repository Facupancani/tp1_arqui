package springboot.dto;

public class ReporteDto {

    private String nombreCarrera;
    private int anioInscripcion;
    private int inscriptos;
    private int egresados;

    public ReporteDto(String nombreCarrera, int anioInscripcion, int inscriptos, int egresados) {
        this.nombreCarrera = nombreCarrera;
        this.anioInscripcion = anioInscripcion;
        this.inscriptos = inscriptos;
        this.egresados = egresados;
    }

    @Override
    public String toString() {
        return "ReporteDto{" +
                "nombreCarrera='" + nombreCarrera + '\'' +
                ", anioInscripcion=" + anioInscripcion +
                ", inscriptos=" + inscriptos +
                ", egresados=" + egresados +
                '}';
    }

}

