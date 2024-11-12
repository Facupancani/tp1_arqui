package org.monopatin.reporteservice.controllers;

import org.apache.coyote.Response;
import org.monopatin.reporteservice.dto.MonopatinDTO;
import org.monopatin.reporteservice.dto.MonopatinReporteDTO;
import org.monopatin.reporteservice.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    /* =========================================================== */
    /* A) Como encargado de mantenimiento quiero poder generar un reporte de uso de monopatines por
    /*  kilómetros para establecer si un monopatín requiere de mantenimiento. Este reporte debe poder
    /*  onfigurarse para incluir (o no) los tiempos de pausa.
    /* =========================================================== */
    /* 11) Generar reporte de uso de monopatines
    /* =========================================================== */
    @GetMapping("/reporte")
    public ResponseEntity<?> obtenerReportePorKilometros(@RequestParam(value = "incluirTiempoPausa", defaultValue = "false") boolean incluirTiempoPausa){
            List<MonopatinReporteDTO> reportes = reporteService.generarReporteDeUso(incluirTiempoPausa);
            return new ResponseEntity<>(reportes, HttpStatus.OK);
    }

}
