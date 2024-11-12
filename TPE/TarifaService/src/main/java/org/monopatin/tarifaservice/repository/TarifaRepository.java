package org.monopatin.tarifaservice.repository;

import org.monopatin.tarifaservice.model.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface TarifaRepository extends JpaRepository<Tarifa,Long> {

    @Query("SELECT t FROM Tarifa t WHERE t.fechaInicioVigencia <= :fecha AND (t.fechaFinVigencia IS NULL OR t.fechaFinVigencia >= :fecha)")
    Tarifa findTarifaVigente(@Param("fecha") LocalDate fecha);

    @Query("SELECT t FROM Tarifa t ORDER BY t.idTarifa DESC")
    Tarifa findLatestTarifa();

    Tarifa findTopByFechaInicioVigenciaLessThanEqualOrderByFechaInicioVigenciaDesc(LocalDate fecha);


}
