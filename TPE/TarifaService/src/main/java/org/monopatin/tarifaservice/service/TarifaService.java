package org.monopatin.tarifaservice.service;

import org.monopatin.tarifaservice.model.Tarifa;
import org.monopatin.tarifaservice.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TarifaService {

    @Autowired
    private TarifaRepository tarifaRepository;

    public Tarifa getTarifa() {
        return tarifaRepository.findLatestTarifa();
    }

    public Tarifa actualizarTarifa(Tarifa nuevaTarifa){
        Tarifa tarifa = tarifaRepository.findLatestTarifa();
        if(tarifa == null){
            tarifa = new Tarifa();
        }
        tarifa.setTarifaPorMinuto(nuevaTarifa.getTarifaPorMinuto());
        tarifa.setTarifaExtra(nuevaTarifa.getTarifaExtra());
        return tarifaRepository.save(tarifa);
    }

}