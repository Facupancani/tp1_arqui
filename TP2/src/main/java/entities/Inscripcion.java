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

    public Inscripcion(int id, Estudiante estudiante, Carrera carrera, int anioIngreso, int anioEgreso, boolean graduado) {
        this.id = id;
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.anioIngreso = anioIngreso;
        this.anioEgreso = anioEgreso;
        this.graduado = graduado;
    }
}