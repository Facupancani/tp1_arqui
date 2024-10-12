package springboot.controllers;

import springboot.entities.Inscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.services.InscripcionService;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Inscripcion inscripcion) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inscripcionService.save(inscripcion));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"uno o mas campos son invalidos\"}");
        }
    }

}
