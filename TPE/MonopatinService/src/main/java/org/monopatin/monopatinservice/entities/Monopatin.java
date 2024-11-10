package org.monopatin.monopatinservice.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Monopatin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMonopatin;

    private String modelo;
    private Double kilometraje = 0.0;
    private Long tiempoDeUso = 0L;
    private Long tiempoEnPausa = 0L;
    private String estado = "disponible"; // "disponible", "en_uso", "en_mantenimiento"
    private Boolean enMantenimiento = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUbicacion")
    private Ubicacion ubicacion;

    @ManyToOne
    @JoinColumn(name = "idParada")
    private Parada paradaActual;

}

