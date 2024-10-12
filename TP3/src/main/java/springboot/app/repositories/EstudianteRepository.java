package springboot.app.repositories;

import springboot.app.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    @Query("SELECT e FROM Estudiante e ORDER BY e.apellido ASC")
    List<Estudiante> findAllWithOrder();

    @Query("SELECT e FROM Estudiante e WHERE e.nroLibreta = :nroLibreta")
    Estudiante findByNroLibreta(@Param("nroLibreta") int nroLibreta);

    @Query("SELECT e FROM Estudiante e WHERE LOWER(e.genero) = LOWER(:genero)")
    List<Estudiante> findByGenero(@Param("genero") String genero);

    @Query("SELECT e FROM Estudiante e JOIN e.inscripciones i WHERE i.carrera.idCarrera = :carreraId AND LOWER(e.ciudadResidencia) = LOWER(:ciudad)")
    List<Estudiante> findByCarreraCiudad(@Param("carreraId") int carreraId, @Param("ciudad") String ciudad);

    @Query("SELECT e FROM Estudiante e WHERE e.nroDocumento = :nroDocumento")
    Estudiante findByNroDocumento(@Param("nroDocumento") int nroDocumento);

}
