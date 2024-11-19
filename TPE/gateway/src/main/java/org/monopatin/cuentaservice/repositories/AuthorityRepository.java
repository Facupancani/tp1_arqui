package org.monopatin.cuentaservice.repositories;

import org.monopatin.cuentaservice.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

    
} 