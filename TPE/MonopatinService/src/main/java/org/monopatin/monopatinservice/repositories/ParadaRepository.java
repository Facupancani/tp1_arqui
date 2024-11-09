package org.monopatin.monopatinservice.repositories;

import org.monopatin.monopatinservice.entities.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long> {
}
