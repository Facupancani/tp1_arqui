package entidades;

import javax.persistence.*;
import java.util.List;

@Entity
public class Carrera {

    @Id
    private int id;
    @Column
    private String nombre;
    @OneToMany(mappedBy = "carrera")
    private List<Inscripcion> inscripciones;

    public Carrera(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

}
