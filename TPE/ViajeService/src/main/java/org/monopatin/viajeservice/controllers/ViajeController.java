package org.monopatin.viajeservice.controllers;

import org.monopatin.viajeservice.entities.Viaje;
import org.monopatin.viajeservice.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viajes")
public class ViajeController {

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

}
