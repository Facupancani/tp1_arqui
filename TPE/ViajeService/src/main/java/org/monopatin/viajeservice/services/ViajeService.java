package org.monopatin.viajeservice.services;

import org.monopatin.viajeservice.dto.MonopatinDTO;
import org.monopatin.viajeservice.dto.TarifaDTO;
import org.monopatin.viajeservice.entities.Viaje;
import org.monopatin.viajeservice.repositories.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String CUENTA_SERVICE_URL = "http://localhost:8002/cuentas";
    private static final String MONOPATIN_SERVICE_URL = "http://localhost:8001/monopatines";
    private static final String TARIFA_SERVICE_URL = "http://localhost:8004/tarifa";

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

        TarifaDTO tarifa = restTemplate.getForObject(TARIFA_SERVICE_URL, TarifaDTO.class);

        // Si se excedio el tiempo de pausa maximo, se comienza a cobrar una tarifa extra
        if(viaje.getFechaHoraTiempoPausaMaximoExcedido() != null){
            Long duracionDesdeInicioHastaTiempoPausaExcedido = ChronoUnit.MINUTES.between(viaje.getFechaHoraInicio(), viaje.getFechaHoraTiempoPausaMaximoExcedido());
            Long duracionDesdeTiempoPausaExcedidoHastaFin = ChronoUnit.MINUTES.between(viaje.getFechaHoraTiempoPausaMaximoExcedido(), viaje.getFechaHoraFin());

            Double tarifaSimple = duracionDesdeInicioHastaTiempoPausaExcedido * tarifa.getTarifaPorMinuto();
            Double tarifaExtra = duracionDesdeTiempoPausaExcedidoHastaFin * tarifa.getTarifaExtra();

            return tarifaSimple + tarifaExtra;
        }

        Long duracion = ChronoUnit.MINUTES.between(viaje.getFechaHoraInicio(), viaje.getFechaHoraFin());
        return duracion*tarifa.getTarifaPorMinuto();
    }

    public List<MonopatinDTO> obtenerMonopatinesConViajes(int anio, Long cantViajes){
        List<Object[]> resultados = viajeRepository.findMonopatinesConViajes(anio, cantViajes);

        return resultados.stream()
                .map(resultado -> new MonopatinDTO(
                        (Long) resultado[0],
                        (Long) resultado[1])
                )
                .collect(Collectors.toList());
    }

    public List<Viaje> getViajes(){
        List<Viaje> viajes = viajeRepository.findAll();
        return viajes;
    }

    public Double getViajesEntre(int anio, int mesInicio, int mesFin) {
        return viajeRepository.findTotalFacturadoByAnioAndMesRange(anio, mesInicio, mesFin);
    }

}