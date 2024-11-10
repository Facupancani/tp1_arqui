package org.monopatin.reporteservice.services;

import org.monopatin.reporteservice.dto.MonopatinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String MONOPATIN_SERVICE_URL = "http://localhost:8001/monopatines";

    /* =========================================================== */
    /* 11) Generar reporte de uso de monopatines por kilómetros
    /* =========================================================== */
    public List<MonopatinDTO> generarReportePorKilometros(){
        // Get all monopatines from MonopatinService REST
        ResponseEntity<MonopatinDTO[]> response = restTemplate.getForEntity(MONOPATIN_SERVICE_URL, MonopatinDTO[].class);
        MonopatinDTO[] monopatines = response.getBody();

        // Retorno una lista de monopatines ordenados por Kilometraje
        return Arrays.stream(monopatines)
                .sorted(Comparator.comparingDouble(MonopatinDTO::getKilometraje).reversed())
                .collect(Collectors.toList());
    }

    /* =========================================================== */
    /* 12) Generar reporte de uso de monopatines por tiempo con pausas
    /* =========================================================== */
    public List<MonopatinDTO> generarReportePorTiempoConPausas(){
        // Get all monopatines from MonopatinService REST
        ResponseEntity<MonopatinDTO[]> response = restTemplate.getForEntity(MONOPATIN_SERVICE_URL, MonopatinDTO[].class);
        MonopatinDTO[] monopatines = response.getBody();

        // Retorno una lista de monopatines ordenados por Tiempo de uso CON pausas
        return Arrays.stream(monopatines)
                .sorted(Comparator.comparingLong(MonopatinDTO::getTiempoConPausas).reversed())
                .collect(Collectors.toList());

    }

    /* =========================================================== */
    /* 13) Generar reporte de uso de monopatines por tiempo sin pausas
    /* =========================================================== */
    public List<MonopatinDTO> generarReportePorTiempoSinPausas(){
        // Get all monopatines from MonopatinService REST
        ResponseEntity<MonopatinDTO[]> response = restTemplate.getForEntity(MONOPATIN_SERVICE_URL, MonopatinDTO[].class);
        MonopatinDTO[] monopatines = response.getBody();

        // Retorno una lista de monopatines ordenados por Tiempo de uso SIN pausas
        return Arrays.stream(monopatines)
                .sorted(Comparator.comparingLong(MonopatinDTO::getTiempoSinPausas).reversed())
                .collect(Collectors.toList());

    }

}
