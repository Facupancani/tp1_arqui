package entities;

import javax.persistence.*;

@Entity
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="nro_libreta",nullable=false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name="id_carrera")
    private Carrera carrera;

    @Column(nullable=false)
    private int anioIngreso;

    @Column(nullable=false)
    private int anioEgreso;

    @Column(nullable=false)
    private boolean graduado;

    public Inscripcion () {}

    public Inscripcion( Estudiante estudiante, Carrera carrera, int anioIngreso, int anioEgreso, boolean graduado) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.anioIngreso = anioIngreso;
        this.anioEgreso = anioEgreso;
        this.graduado = graduado;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "id=" + id +
                ", estudiante=" + (estudiante != null ? estudiante.getNroLibreta() : "null") +
                ", carrera=" + (carrera != null ? carrera.getNombre() : "null") +
                ", anioIngreso=" + anioIngreso +
                ", anioEgreso=" + anioEgreso +
                ", graduado=" + graduado +
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

    public int getAnioIngreso() {
        return anioIngreso;
    }

    public void setAnioIngreso(int anioIngreso) {
        this.anioIngreso = anioIngreso;
    }

    public int getAnioEgreso() {
        return anioEgreso;
    }

    public void setAnioEgreso(int anioEgreso) {
        this.anioEgreso = anioEgreso;
    }

    public boolean isGraduado() {
        return graduado;
    }

    public void setGraduado(boolean graduado) {
        this.graduado = graduado;
    }
}