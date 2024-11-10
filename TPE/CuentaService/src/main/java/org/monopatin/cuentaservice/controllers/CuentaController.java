package org.monopatin.cuentaservice.controllers;

import org.monopatin.cuentaservice.entities.Cuenta;
import org.monopatin.cuentaservice.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta) {
        Cuenta nuevaCuenta = cuentaService.crearCuenta(cuenta);
        return new ResponseEntity<>(nuevaCuenta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cuenta>> obtenerTodasLasCuentas() {
        List<Cuenta> cuentas = cuentaService.obtenerTodasLasCuentas();
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }

    @GetMapping("/{idCuenta}")
    public ResponseEntity<Cuenta> obtenerCuentaPorId(@PathVariable Long idCuenta) {
        return cuentaService.obtenerCuentaPorId(idCuenta)
                .map(cuenta -> new ResponseEntity<>(cuenta, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{idCuenta}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Long idCuenta, @RequestBody Cuenta cuentaActualizada) {
        try {
            Cuenta cuenta = cuentaService.actualizarCuenta(idCuenta, cuentaActualizada);
            return new ResponseEntity<>(cuenta, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idCuenta}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long idCuenta) {
        cuentaService.eliminarCuenta(idCuenta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{idCuenta}/saldo")
    public ResponseEntity<Cuenta> actualizarSaldo(@PathVariable Long idCuenta, @RequestParam Double monto) {
        try {
            Cuenta cuenta = cuentaService.actualizarSaldo(idCuenta, monto);
            return new ResponseEntity<>(cuenta, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{idCuenta}/desactivar")
    public ResponseEntity<Void> deactivarCuenta(@PathVariable Long idCuenta) {
        cuentaService.desactivarCuenta(idCuenta);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{idCuenta}/reactivar")
    public ResponseEntity<Void> reactivarCuenta(@PathVariable Long idCuenta) {
        cuentaService.reactivarCuenta(idCuenta);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/vincular/{idCuenta}/usuarios/{idUsuario}")
    public ResponseEntity<Cuenta> agregarUsuarioACuenta(@PathVariable Long idCuenta, @PathVariable Long idUsuario) {
        Cuenta cuenta = cuentaService.vincularUsuarioCuenta(idCuenta, idUsuario);
        return new ResponseEntity<>(cuenta, HttpStatus.OK);
    }

}
