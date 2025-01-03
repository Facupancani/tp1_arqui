package springboot.app.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Carrera {

    @Id
    @Column
    private int idCarrera;

    @Column
    private String nombre;

    @Column
    int duracion;

    @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "carrera-inscripcion")
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
