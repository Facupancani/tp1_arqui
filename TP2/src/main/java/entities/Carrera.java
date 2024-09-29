package entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Carrera {

    @Id
    private int id;
    @Column
    private String nombre;
    @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL)
    private List<Inscripcion> inscripciones;

    public Carrera () {}

    public Carrera(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", inscripciones=" + (inscripciones != null ? inscripciones.size() : 0) +
                '}';
    }

}
