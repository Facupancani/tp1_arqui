package Helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

import dao.CarreraDao;
import dao.EstudianteDao;
import dao.InscripcionDao;
import entities.Carrera;
import entities.Estudiante;
import entities.Inscripcion;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.FileReader;

public class PopulateTables {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("registroEstudiantes");
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
