package org.monopatin.adminservice.controllers;

import org.monopatin.adminservice.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
