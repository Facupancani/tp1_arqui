package springboot.app.controllers;

import springboot.app.entities.Inscripcion;
import springboot.app.servicio.InscripcionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionServicio inscripcionService;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Inscripcion inscripcion) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inscripcionService.save(inscripcion));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"uno o mas campos son invalidos\"}");
        }
    }

}
