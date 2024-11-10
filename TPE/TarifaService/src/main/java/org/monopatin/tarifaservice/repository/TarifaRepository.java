package org.monopatin.tarifaservice.repository;

import org.monopatin.tarifaservice.model.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TarifaRepository extends JpaRepository<Tarifa,Long> {

    @Query("SELECT t FROM Tarifa t ORDER BY t.idTarifa DESC")
    Tarifa findLatestTarifa();

}
