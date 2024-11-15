package org.monopatin.tarifaservice.controller;

import org.monopatin.tarifaservice.dto.TarifaRequestDTO;
import org.monopatin.tarifaservice.model.Tarifa;
import org.monopatin.tarifaservice.service.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/tarifa")
public class TarifaController {

    private final TarifaService tarifaService;

    @Autowired
    public TarifaController(TarifaService tarifaService) {
        this.tarifaService = tarifaService;
    }

    @GetMapping()
    public ResponseEntity<Tarifa> getTarifa() {
        Tarifa tarifa = tarifaService.getTarifa();
        return new ResponseEntity<>(tarifa, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> actualizarTarifa(
            @RequestBody TarifaRequestDTO tarifaRequestDTO) {

        // Validación: Fecha de inicio debe ser hoy o futura
        if (tarifaRequestDTO.getFechaInicioVigencia().isBefore(LocalDate.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La fecha de inicio de vigencia debe ser hoy o en el futuro.");
        }

        Tarifa tarifaActualizada = tarifaService.actualizarTarifa(tarifaRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(tarifaActualizada);
    }
}
