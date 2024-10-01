package repositories;

import dto.CarreraConInscriptosDto;
import dto.ReporteDto;
import entities.Carrera;
import entities.Estudiante;
import entities.Inscripcion;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class CarreraRepository implements Repository<Carrera> {

    private EntityManager em;

    public CarreraRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insert(Carrera obj) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        /*try {
            if (!(obj instanceof Carrera)) {
                em.persist(obj);
            } else {
                em.merge(obj);
            }
            transaction.commit();
        } catch (PersistenceException e) {
            transaction.rollback();
            System.out.println("Error al insertar Carrera: " + e.getMessage());
            throw e;
        }*/


        try{
            em.persist(obj);
            transaction.commit();
        }catch (PersistenceException e){
            transaction.rollback();
            throw e;
        }

    }


    @Override
    public void update(Carrera obj) {

    }

    @Override
    public void delete(int id) {

    }

    public List<Carrera> findAll() {
        String query = "SELECT e FROM Carrera e";
        Query q = em.createQuery(query);
        return q.getResultList();
    }

    @Override
    public Carrera findById(int id) {
        return em.find(Carrera.class, id);
    }

    // ej 2f)
    // recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
    public List<CarreraConInscriptosDto> findCarrerasConInscriptos(){
        List<CarreraConInscriptosDto> carreras = new ArrayList<>();

        String query = "SELECT c.idCarrera, c.nombre, SIZE(c.inscripciones) FROM Carrera c WHERE SIZE(c.inscripciones) > 0 ORDER BY SIZE(c.inscripciones) DESC";
        Query q = em.createQuery(query);
        List<Object[]> resultados = q.getResultList();

        for(Object[] row : resultados){
            // CarreraConInscriptosDto(int idCarrera, int inscriptos, String nombreCarrera)
            carreras.add(new CarreraConInscriptosDto(
                    ((Number) row[0]).intValue(),
                    (String) row[1],
                    ((Number) row[2]).intValue()
            ));
        }

        return carreras;
    }

    // ej 3)
    // Generar un reporte de las carreras, que para cada carrera incluya información de los
    // inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar
    // los años de manera cronológica.
    public List<ReporteDto> generarReporte() {

        List<ReporteDto> reportes = new ArrayList<>();

        String query = "SELECT c.nombre, i.anioInscripcion, " +
                "COUNT(i.id) AS inscriptos, " +
                "SUM(CASE WHEN i.anioGraduacion IS NOT NULL THEN 1 ELSE 0 END) AS egresados " +
                "FROM Carrera c " +
                "JOIN c.inscripciones i " +
                "GROUP BY c.nombre, i.anioInscripcion " +
                "ORDER BY c.nombre ASC, i.anioInscripcion ASC";

        Query q = em.createQuery(query);

        List<Object[]> resultados = q.getResultList();
        for(Object[] row : resultados){
            // String nombreCarrera, int anioInscripcion, int inscriptos, int egresados)
            reportes.add(new ReporteDto(
                    (String) row[0],
                    ((Number) row[1]).intValue(),
                    ((Number) row[2]).intValue(),
                    ((Number) row[3]).intValue()
            ));
        }

        return reportes;

    }

}
