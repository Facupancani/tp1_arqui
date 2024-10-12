package springboot.app.repositories;

import springboot.app.entities.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

    @Query("SELECT c.nombre, SIZE(c.inscripciones) FROM Carrera c WHERE SIZE(c.inscripciones) > 0 ORDER BY SIZE(c.inscripciones) DESC")
    List<Object[]> findCarrerasConInscriptos();

    @Query("SELECT c.nombre, i.anioInscripcion, " +
            "COUNT(i.id), " +
            "SUM(CASE WHEN i.anioGraduacion IS NOT NULL THEN 1 ELSE 0 END) " +
            "FROM Carrera c " +
            "JOIN c.inscripciones i " +
            "GROUP BY c.nombre, i.anioInscripcion " +
            "ORDER BY c.nombre ASC, i.anioInscripcion ASC")
    List<Object[]> generarReporte();

}
