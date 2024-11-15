package org.monopatin.tarifaservice.service;

import org.monopatin.tarifaservice.model.Tarifa;
import org.monopatin.tarifaservice.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.monopatin.tarifaservice.dto.TarifaRequestDTO;
import java.time.LocalDate;


@Service
public class TarifaService {

    @Autowired
    private TarifaRepository tarifaRepository;

    public Tarifa getTarifa() {
        return tarifaRepository.findLatestTarifa();
    }

    public Tarifa actualizarTarifa(TarifaRequestDTO tarifaRequestDTO) {
        LocalDate fechaInicioVigencia = tarifaRequestDTO.getFechaInicioVigencia();

        // Obtener la tarifa anterior y establecer fecha de fin
        Tarifa tarifaAnterior = tarifaRepository
                .findTopByFechaInicioVigenciaLessThanEqualOrderByFechaInicioVigenciaDesc(LocalDate.now());
        if (tarifaAnterior != null && tarifaAnterior.getFechaFinVigencia() == null) {
            tarifaAnterior.setFechaFinVigencia(fechaInicioVigencia.minusDays(1));
            tarifaRepository.save(tarifaAnterior);
        }

        // Crear y guardar la nueva tarifa
        Tarifa nuevaTarifa = new Tarifa();
        nuevaTarifa.setTarifaPorMinuto(tarifaRequestDTO.getTarifaPorMinuto());
        nuevaTarifa.setTarifaExtra(tarifaRequestDTO.getTarifaExtra());
        nuevaTarifa.setFechaInicioVigencia(fechaInicioVigencia);
        return tarifaRepository.save(nuevaTarifa);
    }
}
