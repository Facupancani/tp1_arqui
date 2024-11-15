package org.monopatin.monopatinservice.controllers;

import org.monopatin.monopatinservice.dto.ReporteUsoMonopatinDTO;
import org.monopatin.monopatinservice.entities.Monopatin;
import org.monopatin.monopatinservice.services.MonopatinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/monopatines")
public class MonopatinController {

    @Autowired
    private MonopatinService monopatinService;

    /* ======================================= */
    /* 1) Registrar monopatín en mantenimiento
    /* ======================================= */
    @PutMapping("/{idMonopatin}/mantenimiento")
    public ResponseEntity<?> registrarMantenimiento(@PathVariable Long idMonopatin){
        try{
            Monopatin monopatin = monopatinService.registrarMantenimiento(idMonopatin);
            return ResponseEntity.status(HttpStatus.OK).body(monopatin);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /* ======================================= */
    /* 2) Finalizar mantenimiento de monopatin
    /* ======================================= */
    @PutMapping("/{idMonopatin}/finalizar-mantenimiento")
    public ResponseEntity<?> finalizarantenimiento(@PathVariable Long idMonopatin){
        try{
            Monopatin monopatin = monopatinService.finalizarMantenimiento(idMonopatin);
            return ResponseEntity.status(HttpStatus.OK).body(monopatin);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /* ======================================= */
    /* 3) Ubicar monopatín en parada
    /* ======================================= */
    @PutMapping("/{idMonopatin}/parada/{idParada}")
    public ResponseEntity<?> ubicarEnParada(@PathVariable Long idMonopatin, @PathVariable Long idParada){
        try{
            Monopatin monopatin = monopatinService.asignarParada(idMonopatin, idParada);
            return ResponseEntity.status(HttpStatus.OK).body(monopatin);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /* ======================================= */
    /* 4) Agregar monopatín
    /* ======================================= */
    @PostMapping
    public ResponseEntity<?> agregarMonopatin(@RequestBody Monopatin monopatin){
        try{
            Monopatin nuevoMonopatin = monopatinService.agregarMonopatin(monopatin);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMonopatin);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /* ======================================= */
    /* 5) Quitar monopatín
    /* ======================================= */
    @DeleteMapping("/{idMonopatin}")
    public ResponseEntity<?> eliminarMonopatin(@PathVariable Long idMonopatin){
        try{
            monopatinService.eliminarMonopatin(idMonopatin);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarMonopatines(){
        try{
            List<Monopatin> monopatines = monopatinService.getAll();
            return ResponseEntity.status(HttpStatus.OK).body(monopatines);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{idMonopatin}/disponible")
    public ResponseEntity<Boolean> monopatinDisponible(@PathVariable Long idMonopatin){
        Boolean disponible = monopatinService.monopatinDisponible(idMonopatin);
        return new ResponseEntity<>(disponible, HttpStatus.OK);
    }

    @GetMapping("/reporte-uso")
    public ResponseEntity<?> generarReporteDeUso(@RequestParam(defaultValue = "false") boolean incluirTiempoPausa) {
        try {
            List<ReporteUsoMonopatinDTO> reporte = monopatinService.generarReporteDeUso(incluirTiempoPausa);
            return ResponseEntity.status(HttpStatus.OK).body(reporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PatchMapping("/{idMonopatin}/activar")
    public ResponseEntity<?> activarMonopatin(@PathVariable Long idMonopatin){
        try{
            Monopatin monopatin = monopatinService.activarMonopatin(idMonopatin);
            return ResponseEntity.status(HttpStatus.OK).body(monopatin);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/{idMonopatin}/desactivar")
    public ResponseEntity<?> desactivarMonopatin(@PathVariable Long idMonopatin){
        try{
            Monopatin monopatin = monopatinService.deactivarMonopatin(idMonopatin);
            return ResponseEntity.status(HttpStatus.OK).body(monopatin);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @GetMapping("/estado")
    public ResponseEntity<Map<String, Long>> obtenerEstadoMonopatines() {
        Map<String, Long> estadoMonopatines = monopatinService.obtenerEstadoMonopatines();
        return new ResponseEntity<>(estadoMonopatines, HttpStatus.OK);
    }

    @GetMapping("/cercanos")
    public ResponseEntity<List<Monopatin>> obtenerMonopatinesCercanos(
            @RequestParam double latitud,
            @RequestParam double longitud,
            @RequestParam(defaultValue = "0.01") double radio) {  // Radio en grados aproximado a 1 km
        List<Monopatin> monopatines = monopatinService.obtenerMonopatinesCercanos(latitud, longitud, radio);
        return ResponseEntity.ok(monopatines);
    }
}
