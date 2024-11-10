package org.monopatin.viajeservice.dto;

import lombok.Data;

@Data
public class MonopatinDTO {

    Long idMonopatin;
    Long cantViajes;

    public MonopatinDTO(){
    }

    public MonopatinDTO(Long idMonopatin, Long cantViajes) {
        this.idMonopatin = idMonopatin;
        this.cantViajes = cantViajes;
    }

}
