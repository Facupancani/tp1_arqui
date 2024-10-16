package springboot.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Data
public class Inscripcion {

    @Id
    @Column
    private int id;

    @ManyToOne
    @JsonBackReference(value = "estudiante-inscripcion")
    @JoinColumn(name="nroDocumento")
    private Estudiante estudiante;

    @ManyToOne
    @JsonBackReference(value = "carrera-inscripcion")
    @JoinColumn(name="idCarrera")
    private Carrera carrera;

    @Column
    private int anioInscripcion;

    @Column
    private int anioGraduacion;

    @Column
    private int antiguedad;

    public Inscripcion () {}

    public Inscripcion(int id, Estudiante estudiante, Carrera carrera, int anioInscripcion, int anioGraduacion, int antiguedad) {
        this.id = id;
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.anioInscripcion = anioInscripcion;
        this.anioGraduacion = anioGraduacion;
        this.antiguedad = antiguedad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public int getAnioInscripcion() {
        return anioInscripcion;
    }

    public void setAnioInscripcion(int anioInscripcion) {
        this.anioInscripcion = anioInscripcion;
    }

    public int getAnioGraduacion() {
        return anioGraduacion;
    }

    public void setAnioGraduacion(int anioGraduacion) {
        this.anioGraduacion = anioGraduacion;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "id=" + id +
                ", estudiante=" + estudiante +
                ", carrera=" + carrera +
                ", anioInscripcion=" + anioInscripcion +
                ", anioGraduacion=" + anioGraduacion +
                ", antiguedad=" + antiguedad +
                '}';
    }

}