package org.monopatin.viajeservice.repositories;

import org.monopatin.viajeservice.entities.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    @Query("SELECT v.idMonopatin, COUNT(v) as cantViajes " +
            "FROM Viaje v " +
            "WHERE YEAR(v.fechaHoraInicio) = :anio " +
            "GROUP BY v.idMonopatin " +
            "HAVING COUNT(v) > :cantViajes")
    List<Object[]> findMonopatinesConViajes(@Param("anio") int anio, @Param("cantViajes") Long cantViajes);

    @Query("SELECT SUM(v.costo) FROM Viaje v WHERE YEAR(v.fechaHoraInicio) = :anio AND MONTH(v.fechaHoraInicio) BETWEEN :mesInicio AND :mesFin")
    Double findTotalFacturadoByAnioAndMesRange(
            @Param("anio") int anio,
            @Param("mesInicio") int mesInicio,
            @Param("mesFin") int mesFin);
}
