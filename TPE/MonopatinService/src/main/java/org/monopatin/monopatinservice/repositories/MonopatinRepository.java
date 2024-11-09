package org.monopatin.monopatinservice.repositories;

import org.monopatin.monopatinservice.entities.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonopatinRepository extends JpaRepository<Monopatin, Long> {
}
