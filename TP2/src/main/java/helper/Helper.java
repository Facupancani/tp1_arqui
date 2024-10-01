package helper;

import javax.persistence.EntityManager;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import entities.Carrera;
import entities.Estudiante;
import entities.Inscripcion;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.w3c.dom.ls.LSOutput;
import repositories.CarreraRepository;
import repositories.EstudianteRepository;
import repositories.InscripcionRepository;

public class Helper {

    private EntityManager em;

    private CarreraRepository carreraRepository;
    private EstudianteRepository estudianteRepository;
    private InscripcionRepository inscripcionRepository;

    public Helper(EntityManager em, CarreraRepository carreraRepository, EstudianteRepository estudianteRepository, InscripcionRepository inscripcionRepository){
        this.em = em;
        this.carreraRepository = carreraRepository;
        this.estudianteRepository = estudianteRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    public void populateTables(){
        this.populateCarrerasEstudiantes();
        this.populateInscripciones();
    }

    public void dropTables() {
        try {
            em.getTransaction().begin();

            // Elimina la tabla Inscripcion primero (si depende de Estudiante o Carrera)
            em.createNativeQuery("DROP TABLE IF EXISTS Inscripcion CASCADE").executeUpdate();

            // Elimina la tabla Estudiante
            em.createNativeQuery("DROP TABLE IF EXISTS Estudiante CASCADE").executeUpdate();

            // Elimina la tabla Carrera
            em.createNativeQuery("DROP TABLE IF EXISTS Carrera CASCADE").executeUpdate();

            em.getTransaction().commit();
            System.out.println("Tablas eliminadas exitosamente.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al eliminar las tablas: " + e.getMessage());
        }
    }



    private void populateCarrerasEstudiantes() {

        try {

            // cargar carreras
            CSVParser parserCarrera = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/resources/carreras.csv"));
            for (CSVRecord row : parserCarrera) {
                // Carrera(int idCarrera, String nombre,int duracion)
                this.carreraRepository.insert(new Carrera(
                        Integer.parseInt(row.get("id_carrera")),
                        row.get("carrera"),
                        Integer.parseInt(row.get("duracion"))
                ));
            }

            // cargar estudiantes
            CSVParser parserEstudiantes = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/resources/estudiantes.csv"));
            for (CSVRecord row : parserEstudiantes) {
                // Estudiante(int nroDocumento, String nombre, String apellido, int edad, String genero, String ciudadResidencia, int nroLibreta)
                this.estudianteRepository.insert(new Estudiante(
                        Integer.parseInt(row.get("DNI")),
                        row.get("nombre"),
                        row.get("apellido"),
                        Integer.parseInt(row.get("edad")),
                        row.get("genero"),
                        row.get("ciudad"),
                        Integer.parseInt(row.get("LU"))
                ));
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void populateInscripciones() {
        try {
            CSVParser parserInscripciones = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/resources/estudianteCarrera.csv"));
            for (CSVRecord row : parserInscripciones) {
                // Inscripcion(int id, Estudiante estudiante, Carrera carrera, int anioInscripcion, int anioGraduacion, int antiguedad)
                this.inscripcionRepository.insert(new Inscripcion(
                        Integer.parseInt(row.get("id")),
                        estudianteRepository.findByNroDocumento(Integer.parseInt(row.get("id_estudiante"))),
                        carreraRepository.findById(Integer.parseInt(row.get("id_carrera"))),
                        Integer.parseInt(row.get("inscripcion")),
                        Integer.parseInt(row.get("graduacion")),
                        Integer.parseInt(row.get("antiguedad"))
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
