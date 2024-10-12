package springboot.repositories;

import springboot.dto.CarreraConInscriptosDto;
import springboot.dto.ReporteDto;
import springboot.entities.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

    @Query("SELECT c.idCarrera, c.nombre, SIZE(c.inscripciones) FROM Carrera c WHERE SIZE(c.inscripciones) > 0 ORDER BY SIZE(c.inscripciones) DESC")
    public List<CarreraConInscriptosDto> findCarrerasConInscriptos();

    @Query("SELECT c.nombre, i.anioInscripcion, " +
            "COUNT(i.id) AS inscriptos, " +
            "SUM(CASE WHEN i.anioGraduacion IS NOT NULL THEN 1 ELSE 0 END) AS egresados " +
            "FROM Carrera c " +
            "JOIN c.inscripciones i " +
            "GROUP BY c.nombre, i.anioInscripcion " +
            "ORDER BY c.nombre ASC, i.anioInscripcion ASC")
    List<ReporteDto> generarReporte();

    /*private EntityManager em;

    public CarreraRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Carrera insert(Carrera obj) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try{
            em.persist(obj);
            transaction.commit();
        }catch (PersistenceException e){
            transaction.rollback();
            throw e;
        }
        return obj;
    }


    @Override
    public Carrera update(int id, Carrera obj) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
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

    }*/

}
