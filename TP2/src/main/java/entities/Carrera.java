package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCarrera;

    @Column
    private String nombre;

    @Column
    int duracion;

    @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inscripcion> inscripciones;

    public Carrera () {
        this.inscripciones = new ArrayList<>();
    }

    public Carrera(int idCarrera, String nombre,int duracion){
        this.idCarrera = idCarrera;
        this.nombre = nombre;
        this.duracion=duracion;
        this.inscripciones = new ArrayList<>();
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getId() {
        return idCarrera;
    }

    public void setId(int id) {
        this.idCarrera = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "idCarrera=" + idCarrera +
                ", nombre='" + nombre + '\'' +
                ", inscripciones=" + (inscripciones != null ? inscripciones.size() : 0) +
                '}';
    }

}
