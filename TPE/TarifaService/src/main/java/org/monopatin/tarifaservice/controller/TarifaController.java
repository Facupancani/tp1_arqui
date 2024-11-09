package org.monopatin.tarifaservice.controller;

import org.monopatin.tarifaservice.model.Tarifa;
import org.monopatin.tarifaservice.service.TarifaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class TarifaController {

    private final TarifaService tarifaService;

    public TarifaController(TarifaService tarifaService){
        this.tarifaService=tarifaService;
    }

    @GetMapping
    public List<Tarifa> getAllTarifas(){
        return tarifaService.getAllTarifas();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Tarifa> getTarifaById(@PathVariable int id){
        return tarifaService.getTarifaById(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tarifa createTarifa(@RequestBody Tarifa tarifa){
        return tarifaService.saveTarifa(tarifa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarifa(@PathVariable int id){
        tarifaService.deleteTarifa(id);
        return ResponseEntity.noContent().build();
    }
}
