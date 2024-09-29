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

}
