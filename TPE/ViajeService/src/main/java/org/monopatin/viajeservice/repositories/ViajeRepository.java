package org.monopatin.viajeservice.repositories;

import org.monopatin.viajeservice.entities.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {
}
