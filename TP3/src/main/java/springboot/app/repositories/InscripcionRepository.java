package springboot.app.repositories;

import org.springframework.data.jpa.repository.Query;
import springboot.app.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {

    @Query("SELECT COALESCE(MAX(i.id), 0) FROM Inscripcion i")
    int findLastId();

}
