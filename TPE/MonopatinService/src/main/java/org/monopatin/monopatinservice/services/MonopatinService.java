package org.monopatin.monopatinservice.services;

import org.monopatin.monopatinservice.entities.Monopatin;
import org.monopatin.monopatinservice.entities.Parada;
import org.monopatin.monopatinservice.entities.Ubicacion;
import org.monopatin.monopatinservice.repositories.MonopatinRepository;
import org.monopatin.monopatinservice.repositories.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MonopatinService {

    @Autowired
    private MonopatinRepository monopatinRepository;

    @Autowired
    private ParadaRepository paradaRepository;

    /* ======================================= */
    /* 1) Registrar monopatín en mantenimiento
    /* ======================================= */
    public Monopatin registrarMantenimiento(Long idMonopatin){
        Monopatin monopatin = monopatinRepository.findById(idMonopatin)
                .orElseThrow(() -> new RuntimeException("Monopatin no encontrado"));

        if(monopatin.getEnMantenimiento()){
            throw new RuntimeException("El monopatin ya esta en mantenimiento");
        }

        monopatin.setEnMantenimiento(true);
        monopatin.setEstado("en_mantenimiento");

        return monopatinRepository.save(monopatin);
    }

    /* ======================================= */
    /* 2) Finalizar mantenimiento de monopatin
    /* ======================================= */
    public Monopatin finalizarMantenimiento(Long idMonopatin){
        Monopatin monopatin = monopatinRepository.findById(idMonopatin)
                .orElseThrow(() -> new RuntimeException("Monopatin no encontrado"));

        if(!monopatin.getEnMantenimiento()){
            throw new RuntimeException("El monopatin no esta en mantenimiento");
        }

        monopatin.setEnMantenimiento(false);
        monopatin.setEstado("disponible");

        return monopatinRepository.save(monopatin);
    }

    /* ======================================= */
    /* 3) Ubicar monopatín en parada
    /* ======================================= */
    public Monopatin asignarParada(Long idMonopatin, Long idParada){
        Monopatin monopatin = monopatinRepository.findById(idMonopatin)
                .orElseThrow(() -> new RuntimeException("Monopatin no encontrado"));

        Parada parada = paradaRepository.findById(idParada)
                .orElseThrow(() -> new RuntimeException("Parada no encontrada"));

        monopatin.setParadaActual(parada);
        monopatin.setUbicacion(parada.getUbicacion());
        return monopatinRepository.save(monopatin);
    }

    /* ======================================= */
    /* 4) Agregar monopatín
    /* ======================================= */
    public Monopatin agregarMonopatin(Monopatin monopatin) {
        return monopatinRepository.save(monopatin);
    }

    /* ======================================= */
    /* 5) Quitar monopatín
    /* ======================================= */
    public void eliminarMonopatin(Long idMonopatin) {
        if(!monopatinRepository.existsById(idMonopatin)){
            throw new RuntimeException("Monopatin no encontrado");
        }
        monopatinRepository.deleteById(idMonopatin);
    }

    public boolean monopatinDisponible(Long idMonopatin){
        Monopatin monopatin = monopatinRepository.findById(idMonopatin)
                .orElseThrow(() -> new RuntimeException("Monopatin no encontrado"));
        return (!monopatin.getEnMantenimiento()) && (monopatin.getEstado().equals("disponible"));
    }

    public List<Monopatin> getAll(){
        return monopatinRepository.findAll();
    }

    public Monopatin activarMonopatin(Long idMonopatin){
        Monopatin monopatin = monopatinRepository.findById(idMonopatin)
                .orElseThrow(() -> new RuntimeException("Monopatin no encontrado"));
        if(monopatin.getEnMantenimiento()){
            throw new RuntimeException("No se puede activar un monopatin en mantenimiento");
        }
        monopatin.setEstado("en_uso");
        return monopatinRepository.save(monopatin);
    }

    public Monopatin deactivarMonopatin(Long idMonopatin){
        Monopatin monopatin = monopatinRepository.findById(idMonopatin)
                .orElseThrow(() -> new RuntimeException("Monopatin no encontrado"));
        if(monopatin.getEnMantenimiento()){
            throw new RuntimeException("No se puede desactivar un monopatin en mantenimiento");
        }
        monopatin.setEstado("disponible");
        return monopatinRepository.save(monopatin);
    }

    public Map<String, Long> obtenerEstadoMonopatines() {
        Long enOperacion = monopatinRepository.countMonopatinesEnOperacion();
        Long enMantenimiento = monopatinRepository.countMonopatinesEnMantenimiento();

        Map<String, Long> resultado = new HashMap<>();
        resultado.put("enOperacion", enOperacion);
        resultado.put("enMantenimiento", enMantenimiento);

        return resultado;
    }

    public List<Monopatin> obtenerMonopatinesCercanos(double latitud, double longitud, double radio) {
        return monopatinRepository.findMonopatinesCercanos(latitud, longitud, radio);
    }

}
