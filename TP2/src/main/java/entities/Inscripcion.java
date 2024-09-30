package entities;

import javax.persistence.*;

@Entity
public class Inscripcion {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name="nroDocumento",nullable=false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name="id_carrera")
    private Carrera carrera;
    @Column
    private String inscripcion;
    @Column(nullable=false)
    private int graduacion;
    @Column(nullable=false)
    private int antiguedad;

    public Inscripcion () {}

    public Inscripcion(int id, Estudiante estudiante, Carrera carrera,String inscripcion, int graduacion, int antiguedad) {
        this.id=id;
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.inscripcion=inscripcion;
        this.graduacion=graduacion;
        this.antiguedad=antiguedad;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "id=" + id +
                ", estudiante=" + (estudiante != null ? estudiante.getNroLibreta() : "null") +
                ", carrera=" + (carrera != null ? carrera.getNombre() : "null") +
                ", Graduacion=" + graduacion +
                ", Antiguedad=" + antiguedad +
                '}';
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

    public String getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(String inscripcion) {
        this.inscripcion = inscripcion;
    }

    public int getGraduacion() {
        return graduacion;
    }

    public void setGraduacion(int graduacion) {
        this.graduacion = graduacion;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }
}