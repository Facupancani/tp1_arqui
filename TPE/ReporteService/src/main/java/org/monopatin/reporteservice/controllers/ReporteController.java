package org.monopatin.reporteservice.controllers;

import org.apache.coyote.Response;
import org.monopatin.reporteservice.dto.MonopatinDTO;
import org.monopatin.reporteservice.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    /* =========================================================== */
    /* 11) Generar reporte de uso de monopatines por kilómetros
    /* =========================================================== */
    @GetMapping("/kilometros")
    public ResponseEntity<?> obtenerReportePorKilometros(){
        try{
            List<MonopatinDTO> reportes = reporteService.generarReportePorKilometros();
            return ResponseEntity.status(HttpStatus.OK).body(reportes);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /* =========================================================== */
    /* 12) Generar reporte de uso de monopatines por tiempo con pausas
    /* =========================================================== */
    @GetMapping("/tiempo-con-pausas")
    public ResponseEntity<?> obtenerReportePorTiempoConPausas(){
        try{
            List<MonopatinDTO> reportes = reporteService.generarReportePorTiempoConPausas();
            return ResponseEntity.status(HttpStatus.OK).body(reportes);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /* =========================================================== */
    /* 13) Generar reporte de uso de monopatines por tiempo sin pausas
    /* =========================================================== */
    @GetMapping("/tiempo-sin-pausas")
    public ResponseEntity<?> obtenerReportePorTiempoSinPausas(){
        try{
            List<MonopatinDTO> reportes = reporteService.generarReportePorTiempoSinPausas();
            return ResponseEntity.status(HttpStatus.OK).body(reportes);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
