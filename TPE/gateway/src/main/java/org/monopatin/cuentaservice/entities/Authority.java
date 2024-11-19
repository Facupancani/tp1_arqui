package org.monopatin.cuentaservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Authority {
    @Id
    private String name;
}
