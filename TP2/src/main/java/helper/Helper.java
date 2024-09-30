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

    public void populateTables() {

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

            CSVParser parserInscripciones = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/resources/estudianteCarrera.csv"));
            for (CSVRecord row : parserInscripciones) {
                // Inscripcion(int id, Estudiante estudiante, Carrera carrera, int anioInscripcion, int anioGraduacion, int antiguedad)
                this.inscripcionRepository.insert(new Inscripcion(
                        Integer.parseInt(row.get("id")),
                        estudianteRepository.findById(Integer.parseInt(row.get("id_estudiante"))),
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

    /*static EntityManagerFactory emf = Persistence.createEntityManagerFactory("registroEstudiantes");
    static EntityManager em = emf.createEntityManager();

    static EstudianteDao estudianteDAO = new EstudianteDao(em);
    static CarreraDao carreraDAO = new CarreraDao(em);
    static InscripcionDao inscripcionDAO = new InscripcionDao(em);
    static EntityTransaction transaction = em.getTransaction();

    public static void populateEstudiantes(){
        try{
            CSVParser parserEstudiante = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/data/estudiantes.csv"));
            for(CSVRecord row : parserEstudiante){
                Estudiante e = new Estudiante(Integer.parseInt(row.get("LU")), row.get("nombre"), row.get("apellido"),Integer.parseInt(row.get("edad")),row.get("genero"),Integer.parseInt(row.get("DNI")),row.get("ciudad"));
                estudianteDAO.insert(e);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void populateCarreras(){
        try{
            CSVParser parserCarrera = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/data/carreras.csv"));
            for(CSVRecord row : parserCarrera){
                Carrera c = new Carrera(Integer.parseInt(row.get("id_carrera")),row.get("carrera"),Integer.parseInt(row.get("duracion")));
                carreraDAO.insert(c);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void populateInscripciones(){
        try{
            CSVParser parserInscripcion = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/data/estudianteCarrera.csv"));
           for(CSVRecord row : parserInscripcion){
               Estudiante e=estudianteDAO.findByDocumento(71779527);
                System.out.println("pruebaaaa-> "+estudianteDAO.findByDocumento(Integer.parseInt(row.get("id_estudiante"))));
                Inscripcion i =new Inscripcion(Integer.parseInt(row.get("id")),e,carreraDAO.find(Integer.parseInt(row.get("id_carrera"))),row.get("inscripcion"),Integer.parseInt(row.get("graduacion")),Integer.parseInt(row.get("antiguedad")));
                inscripcionDAO.insert(i);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

   /* public static void populateTables() {
        try{

            CSVParser parserEstudiante = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/data/estudiantes.csv"));
            for(CSVRecord row : parserEstudiante){
                Estudiante e = new Estudiante(Integer.parseInt(row.get("LU")), row.get("nombre"), row.get("apellido"),Integer.parseInt(row.get("edad")),row.get("genero"),Integer.parseInt(row.get("DNI")),row.get("ciudad"));
                estudianteDAO.insert(e);
            }

            CSVParser parserCarrera = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/data/carreras.csv"));
            for(CSVRecord row : parserCarrera){
                Carrera c = new Carrera(Integer.parseInt(row.get("id_carrera")),row.get("carrera"),Integer.parseInt(row.get("duracion")));
                carreraDAO.insert(c);
            }

            CSVParser parserInscripcion = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/data/estudianteCarrera.csv"));
            for(CSVRecord row : parserInscripcion){
                System.out.println(estudianteDAO.findByDocumento(Integer.parseInt(row.get("id_estudiante"))));
                Inscripcion i =new Inscripcion(Integer.parseInt(row.get("id")),estudianteDAO.findByDocumento(Integer.parseInt(row.get("id_estudiante"))),carreraDAO.find(Integer.parseInt(row.get("id_carrera"))),row.get("inscripcion"),Integer.parseInt(row.get("graduacion")),Integer.parseInt(row.get("antiguedad")));
                inscripcionDAO.insert(i);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }*/

}
