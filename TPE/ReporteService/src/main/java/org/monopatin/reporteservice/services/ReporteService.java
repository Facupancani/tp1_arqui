package org.monopatin.reporteservice.services;

import org.monopatin.reporteservice.dto.MonopatinDTO;
import org.monopatin.reporteservice.dto.MonopatinReporteDTO;
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
    /* Generar reporte de uso de monopatines
    /* =========================================================== */
    public List<MonopatinReporteDTO> generarReporteDeUso(boolean incluirTiempoPausa){
        // Get all monopatines from MonopatinService REST
        ResponseEntity<MonopatinDTO[]> response = restTemplate.getForEntity(MONOPATIN_SERVICE_URL, MonopatinDTO[].class);
        MonopatinDTO[] monopatines = response.getBody();

        if (monopatines == null) {
            throw new RuntimeException("No hay monopatines");
        }

        return Arrays.stream(monopatines)
                .map(monopatin ->{
                    Long tiempoDeUso = incluirTiempoPausa ? (monopatin.getTiempoDeUso() + monopatin.getTiempoEnPausa()) : monopatin.getTiempoDeUso();
                    MonopatinReporteDTO reporte = new MonopatinReporteDTO(
                            monopatin.getIdMonopatin(),
                            monopatin.getKilometraje(),
                            tiempoDeUso
                    );
                    return reporte;
                })
                .sorted((m1, m2) -> Double.compare(m2.getKilometraje(), m1.getKilometraje()))
                .collect(Collectors.toList());

    }

}
