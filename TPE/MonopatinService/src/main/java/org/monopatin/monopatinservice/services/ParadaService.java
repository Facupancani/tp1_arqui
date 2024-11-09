package org.monopatin.monopatinservice.services;

import org.monopatin.monopatinservice.entities.Parada;
import org.monopatin.monopatinservice.repositories.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParadaService {

    @Autowired
    private ParadaRepository paradaRepository;

    /* ======================================= */
    /* 6) Registrar parada
    /* ======================================= */
    public Parada registrarParada(Parada parada) {
        return paradaRepository.save(parada);
    }

    /* ======================================= */
    /* 7) Quitar parada
    /* ======================================= */
    public void eliminarParada(Long idParada) {
        if(!paradaRepository.existsById(idParada)){
            throw new RuntimeException("Parada no encontrada");
        }
        paradaRepository.deleteById(idParada);
    }


}
