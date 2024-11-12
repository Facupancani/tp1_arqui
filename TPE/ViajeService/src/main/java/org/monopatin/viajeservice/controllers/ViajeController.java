package org.monopatin.viajeservice.controllers;

import org.monopatin.viajeservice.dto.MonopatinDTO;
import org.monopatin.viajeservice.entities.Viaje;
import org.monopatin.viajeservice.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.monopatin.viajeservice.repositories.ViajeRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/viajes")
public class ViajeController {

    @Autowired
    private ViajeRepository viajeRepo;

    @Autowired
    private ViajeService viajeService;

    @PostMapping("/iniciar")
    public ResponseEntity<Viaje> iniciarViaje(
            @RequestParam Long idCuenta,
            @RequestParam Long idUsuario,
            @RequestParam Long idMonopatin,
            @RequestParam Long idParadaInicio) {
        Viaje viaje = viajeService.iniciarViaje(idCuenta, idUsuario, idMonopatin, idParadaInicio);
        return new ResponseEntity<>(viaje, HttpStatus.CREATED);
    }

    @PostMapping("/finalizar/{idViaje}")
    public ResponseEntity<Viaje> finalizarViaje(
            @PathVariable Long idViaje,
            @RequestParam Long idParadaFin,
            @RequestParam Double kilometrosRecorridos) {
        Viaje viaje = viajeService.finalizarViaje(idViaje, idParadaFin, kilometrosRecorridos);
        return new ResponseEntity<>(viaje, HttpStatus.OK);
    }

    @GetMapping("/monopatines/mas-viajes")
    public ResponseEntity<List<MonopatinDTO>> obtenerMonopatinesConMasViajes(
            @RequestParam int anio,
            @RequestParam long numViajes) {
        List<MonopatinDTO> resultado = viajeService.obtenerMonopatinesConViajes(anio, numViajes);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping
    public List<Viaje> getAllViajes(){
        return viajeService.getViajes();
    }

    @GetMapping("/facturado-entre")
    public Double getFacturadoEntre(
            @RequestParam int anio,
            @RequestParam int mesInicio,
            @RequestParam int mesFin) {
        return viajeService.getViajesEntre(anio, mesInicio, mesFin);
    }

}
