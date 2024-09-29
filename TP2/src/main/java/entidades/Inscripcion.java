package entidades;

import javax.persistence.*;
import java.util.List;

@Entity
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInscripcion;
    @ManyToOne
    @JoinColumn(name="num_libreta",nullable=false)
    private Estudiante estudiante;
    @ManyToOne
    @JoinColumn(name="id_carrera")
    private Carrera carrera;
    @Column(nullable=false)
    private int antiguedad;
    @Column(nullable=false)
    private boolean graduado;


    public Inscripcion(int idInscripcion, Estudiante estudiante, Carrera carrera, int antiguedad, boolean graduado) {
        this.idInscripcion = idInscripcion;
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.antiguedad = antiguedad;
        this.graduado = graduado;
    }
}