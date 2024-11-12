package org.monopatin.cuentaservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombre;
    private String apellido;
    private String email;
    private String nroTelefono;

    @ManyToMany(mappedBy = "usuarios")
    @JsonIgnore
    private List<Cuenta> cuentas;

}
