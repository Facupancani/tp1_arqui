package org.monopatin.viajeservice.services;

import org.monopatin.viajeservice.entities.Viaje;
import org.monopatin.viajeservice.repositories.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String CUENTA_SERVICE_URL = "http://localhost:8002/cuentas";
    private static final String MONOPATIN_SERVICE_URL = "http://localhost:8001/monopatines";

    public Viaje iniciarViaje(Long idCuenta, Long idUsuario, Long idMonopatin, Long idParadaInicio){

        String cuentaUrl = CUENTA_SERVICE_URL + "/" + idCuenta + "/saldo";
        Double saldo = restTemplate.getForObject(cuentaUrl, Double.class);

        if(saldo == null || saldo <= 0){
            throw new RuntimeException("Saldo insuficiente para iniciar el viaje.");
        }

        String monopatinUrl = MONOPATIN_SERVICE_URL + "/" + idMonopatin + "/disponible";
        Boolean disponible = restTemplate.getForObject(monopatinUrl, Boolean.class);

        if (disponible == null || !disponible) {
            throw new RuntimeException("El monopatín no está disponible.");
        }

        Viaje viaje = new Viaje();

        viaje.setIdCuenta(idCuenta);
        viaje.setIdUsuario(idUsuario);
        viaje.setIdMonopatin(idMonopatin);
        viaje.setFechaHoraInicio(LocalDateTime.now());
        viaje.setIdParadaInicio(idParadaInicio);

        viajeRepository.save(viaje);
        restTemplate.patchForObject(MONOPATIN_SERVICE_URL + "/" + idMonopatin +"/activar", null, Void.class);

        return viaje;
    }

    public Viaje finalizarViaje(Long idViaje, Long idParadaFin, Double kilometrosRecorridos){

        Viaje viaje = viajeRepository.findById(idViaje)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado."));

        viaje.setFechaHoraFin(LocalDateTime.now());
        viaje.setKilometrosRecorridos(kilometrosRecorridos);
        viaje.setIdParadaFin(idParadaFin);
        viaje.setEnCurso(false);

        Double costo = this.calcularCosto(viaje);
        viaje.setCosto(costo);

        return viajeRepository.save(viaje);

    }

    private Double calcularCosto(Viaje viaje){
        Long duracion = ChronoUnit.MINUTES.between(viaje.getFechaHoraInicio(), viaje.getFechaHoraFin());
        Double tarifaPorMinuto = 1.0; // TODO: OBTENER TARIFA DE TarifaService
        return duracion*tarifaPorMinuto;
    }

}