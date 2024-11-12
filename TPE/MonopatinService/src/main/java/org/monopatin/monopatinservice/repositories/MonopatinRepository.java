package org.monopatin.monopatinservice.repositories;

import org.monopatin.monopatinservice.entities.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonopatinRepository extends JpaRepository<Monopatin, Long> {

    @Query("SELECT COUNT(m) FROM Monopatin m WHERE m.estado = 'en_uso'")
    Long countMonopatinesEnOperacion();

    @Query("SELECT COUNT(m) FROM Monopatin m WHERE m.estado = 'en_mantenimiento'")
    Long countMonopatinesEnMantenimiento();

    @Query("""
                SELECT m FROM Monopatin m
                JOIN m.ubicacion u
                WHERE m.estado = 'disponible'
                AND SQRT(POW((u.latitud - :latitud), 2) + POW((u.longitud - :longitud), 2)) < :radio
            """)
    List<Monopatin> findMonopatinesCercanos(@Param("latitud") double latitud,
                                            @Param("longitud") double longitud,
                                            @Param("radio") double radio);
}
