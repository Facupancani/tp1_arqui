package org.monopatin.cuentaservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuenta;

    private String nombreTitular;
    private String email;
    private Double saldo = 0.0;
    private LocalDate fechaAlta;
    private Boolean activa = true;

    @ManyToMany
    @JoinTable(
            name = "cuenta_usuario",
            joinColumns = @JoinColumn(name = "idCuenta"),
            inverseJoinColumns = @JoinColumn(name = "idUsuario")
    )
    private List<Usuario> usuarios;

}
