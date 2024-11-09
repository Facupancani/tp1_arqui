package org.monopatin.monopatinservice.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Parada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParada;

    private String nombre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUbicacion")
    private Ubicacion ubicacion;

}
