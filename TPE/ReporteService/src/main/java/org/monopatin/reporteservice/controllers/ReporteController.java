package org.monopatin.reporteservice.controllers;

import org.apache.coyote.Response;
import org.monopatin.reporteservice.dto.MonopatinDTO;
import org.monopatin.reporteservice.dto.MonopatinReporteDTO;
import org.monopatin.reporteservice.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    /* =========================================================== */
    /* 11) Generar reporte de uso de monopatines
    /* =========================================================== */
    @GetMapping("/reporte")
    public ResponseEntity<?> obtenerReportePorKilometros(@RequestParam(value = "incluirTiempoPausa", defaultValue = "false") boolean incluirTiempoPausa){
            List<MonopatinReporteDTO> reportes = reporteService.generarReporteDeUso(incluirTiempoPausa);
            return new ResponseEntity<>(reportes, HttpStatus.OK);
    }

}
