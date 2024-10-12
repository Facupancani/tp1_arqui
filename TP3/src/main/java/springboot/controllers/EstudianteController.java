package springboot.controllers;

import springboot.entities.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.services.EstudianteService;


@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findAllWithOrder());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"estudianteId '"+id+"' no encontrada\"");
        }
    }

    @GetMapping("/libreta/{nroLibreta}")
    public ResponseEntity<?> getByLibreta(@PathVariable int nroLibreta) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findByNroLibreta(nroLibreta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"nroLibreta '"+nroLibreta+"' no encontrada\"");
        }
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<?> getById(@PathVariable String genero) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findByGenero(genero));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"genero '"+genero+"' invalido\"");
        }
    }

    @GetMapping("/carrera/{carreraId}/ciudad/{ciuadd}")
    public ResponseEntity<?> getByCarreraCiudad(@PathVariable int carreraId, String ciudad) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findByCarreraCiudad(carreraId, ciudad));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"sin resultados\"");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Estudiante estudiante) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.save(estudiante));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"uno o mas campos son invalidos\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Estudiante estudiante) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.update(id, estudiante));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"uno o mas campos son invalidos\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"estudianteId '"+id+"' no encontrada\"");
        }
    }

}
