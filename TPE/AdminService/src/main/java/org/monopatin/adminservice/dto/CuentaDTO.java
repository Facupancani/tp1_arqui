package org.monopatin.adminservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CuentaDTO {

    private Long idCuenta;
    private String nombreTitular;
    private String email;
    private Double saldo;
    private LocalDate fechaAlta;
    private Boolean activa;

}
