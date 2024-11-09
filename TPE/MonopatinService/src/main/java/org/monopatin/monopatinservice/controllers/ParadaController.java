package org.monopatin.monopatinservice.controllers;

import org.monopatin.monopatinservice.entities.Monopatin;
import org.monopatin.monopatinservice.entities.Parada;
import org.monopatin.monopatinservice.services.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paradas")
public class ParadaController {

    @Autowired
    private ParadaService paradaService;

    /* ======================================= */
    /* 6) Registrar parada
    /* ======================================= */
    @PostMapping
    public ResponseEntity<?> registerParada(@RequestBody Parada parada) {
        try{
            Parada nuevaParada = paradaService.registrarParada(parada);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaParada);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /* ======================================= */
    /* 7) Quitar parada
    /* ======================================= */
    @DeleteMapping("/{idParada}")
    public ResponseEntity<?> eliminarParada(@PathVariable Long idParada){
        try{
            paradaService.eliminarParada(idParada);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
