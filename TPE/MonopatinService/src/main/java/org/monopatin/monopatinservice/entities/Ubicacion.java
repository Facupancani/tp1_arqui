package org.monopatin.monopatinservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUbicacion;

    private Double latitud;
    private Double longitud;

    public Ubicacion(){
    }

    public Ubicacion(Double latitud, Double longitud) {
    }

}
