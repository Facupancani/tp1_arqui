package springboot.controllers;

import springboot.entities.Carrera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.services.CarreraService;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.findAll());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/conInscriptos")
    public ResponseEntity<?> getCarrerasConInscriptos() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.findCarrerasConInscriptos());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/reporte")
    public ResponseEntity<?> getReporte() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.generarReporte());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"carreraId '"+id+"' no encontrada\"");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Carrera carrera) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.save(carrera));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"uno o mas campos son invalidos\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Carrera carrera) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.update(id, carrera));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"uno o mas campos son invalidos\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"carreraId '"+id+"' no encontrada\"");
        }
    }



}
