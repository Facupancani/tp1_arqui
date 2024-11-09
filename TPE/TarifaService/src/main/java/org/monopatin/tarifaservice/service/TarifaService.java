package org.monopatin.tarifaservice.service;

import org.monopatin.tarifaservice.model.Tarifa;
import org.monopatin.tarifaservice.repository.TarifaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TarifaService {
    private final TarifaRepository tarifaRepository;

    public TarifaService(TarifaRepository tarifaRepository){
        this.tarifaRepository=tarifaRepository;
    }
    public List<Tarifa>getAllTarifas(){
        return tarifaRepository.findAll();
    }
    public Optional<Tarifa>getTarifaById(int id){
        return tarifaRepository.findById(id);
    }
    public Tarifa saveTarifa(Tarifa tarifa){
        return tarifaRepository.save(tarifa);
    }
    public void deleteTarifa(int id){
        tarifaRepository.deleteById(id);
    }
}
