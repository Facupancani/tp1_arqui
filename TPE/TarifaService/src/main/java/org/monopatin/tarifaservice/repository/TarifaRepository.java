package org.monopatin.tarifaservice.repository;

import org.monopatin.tarifaservice.model.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarifaRepository extends JpaRepository<Tarifa,Integer> {
}
