package org.monopatin.adminservice.dto;

import lombok.Data;

@Data
public class EnOperacionDTO {

    private Long monopatinesEnOperacion;
    private Long monopatinesEnMantenimiento;

    public EnOperacionDTO(Long monopatinesEnOperacion, Long monopatinesEnMantenimiento) {
        this.monopatinesEnOperacion = monopatinesEnOperacion;
        this.monopatinesEnMantenimiento = monopatinesEnMantenimiento;
    }
}
