package org.monopatin.adminservice.controllers;

import org.monopatin.adminservice.dto.EnOperacionDTO;
import org.monopatin.adminservice.dto.MonopatinDTO;
import org.monopatin.adminservice.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PatchMapping("/anular-cuenta/{idCuenta}")
    public ResponseEntity<String> anularCuenta(@PathVariable Long idCuenta) {
        try {
            adminService.anularCuenta(idCuenta);
            return new ResponseEntity<>("Cuenta anulada exitosamente.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/reactivar-cuenta/{idCuenta}")
    public ResponseEntity<String> reactivarCuenta(@PathVariable Long idCuenta) {
        try {
            adminService.reactivarCuenta(idCuenta);
            return new ResponseEntity<>("Cuenta reactivada exitosamente.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/monopatines/mas-viajes")
    public ResponseEntity<List<MonopatinDTO>> obtenerMonopatinesConMasViajes(
            @RequestParam int anio,
            @RequestParam long cantViajes) {
        List<MonopatinDTO> resultado = adminService.obtenerMonopatinesConViajes(anio, cantViajes);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/monopatines/estados")
    public ResponseEntity<EnOperacionDTO> obtenerEstadoMonopatines(){
        EnOperacionDTO resultado = adminService.obtenerEstadoDeMonopatines();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

}
