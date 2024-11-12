package org.monopatin.tarifaservice.service;

import org.monopatin.tarifaservice.model.Tarifa;
import org.monopatin.tarifaservice.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class TarifaService {

    @Autowired
    private TarifaRepository tarifaRepository;

    public Tarifa getTarifa() {
        return tarifaRepository.findLatestTarifa();
    }

    // Actualizar tarifa
    public Tarifa actualizarTarifa(Tarifa tarifa, LocalDate fechaInicioVigencia) {
        // Si hay una tarifa activa, actualizamos la fecha de fin
        Tarifa tarifaAnterior = tarifaRepository.findTopByFechaInicioVigenciaLessThanEqualOrderByFechaInicioVigenciaDesc(LocalDate.now());
        if (tarifaAnterior != null && tarifaAnterior.getFechaFinVigencia() == null) {
            tarifaAnterior.setFechaFinVigencia(LocalDate.now());  // Se termina la vigencia de la tarifa anterior
            tarifaRepository.save(tarifaAnterior);
        }

        // Establecemos la nueva tarifa con la fecha de inicio de vigencia
        tarifa.setFechaInicioVigencia(fechaInicioVigencia);
        return tarifaRepository.save(tarifa);
    }

}