package org.monopatin.tarifaservice.controller;

import org.monopatin.tarifaservice.model.Tarifa;
import org.monopatin.tarifaservice.service.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/tarifa")
public class TarifaController {

    @Autowired
    private final TarifaService tarifaService;

    public TarifaController(TarifaService tarifaService){
        this.tarifaService=tarifaService;
    }

    @GetMapping()
    public ResponseEntity<Tarifa> getTarifa(){
        Tarifa tarifa = tarifaService.getTarifa();
        return new ResponseEntity<>(tarifa, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Tarifa> actualizarTarifa(
            @RequestBody Tarifa tarifa,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicioVigencia) {

        Tarifa tarifaActualizada = tarifaService.actualizarTarifa(tarifa, fechaInicioVigencia);
        return ResponseEntity.status(HttpStatus.OK).body(tarifaActualizada);
    }

}
