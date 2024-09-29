package entidades;

import javax.persistence.*;
import java.util.List;

@Entity
public class Estudiante {

    @Id
    private int id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private int edad;
    @Column
    private String genero;
    @Column
    private int nroDocumento;
    @Column
    private String ciudadResidencia;
    @OneToMany(mappedBy = "carrera")
    private List<Inscripcion> inscripcions;

    public Estudiante(int id, String nombre, String apellido, int edad, String genero, int nroDocumento, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.nroDocumento = nroDocumento;
        this.ciudadResidencia = ciudad;
    }

}
