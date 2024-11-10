package org.monopatin.cuentaservice.services;

import org.monopatin.cuentaservice.entities.Cuenta;
import org.monopatin.cuentaservice.entities.Usuario;
import org.monopatin.cuentaservice.repositories.CuentaRepository;
import org.monopatin.cuentaservice.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Cuenta crearCuenta(Cuenta cuenta){
        return cuentaRepository.save(cuenta);
    }

    public Optional<Cuenta> obtenerCuentaPorId(Long idCuenta){
        return cuentaRepository.findById(idCuenta);
    }

    public List<Cuenta> obtenerTodasLasCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta actualizarCuenta(Long idCuenta, Cuenta cuentaActualizada){
        return cuentaRepository.findById(idCuenta)
                .map(cuenta -> {
                    cuenta.setNombreTitular(cuentaActualizada.getNombreTitular());
                    cuenta.setEmail(cuentaActualizada.getEmail());
                    //cuenta.setSaldo(cuentaActualizada.setSaldo());
                    //cuenta.setActiva(cuentaActualizada.setActiva());
                    return cuentaRepository.save(cuenta);
                }).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    public void eliminarCuenta(Long idCuenta){
        cuentaRepository.deleteById(idCuenta);
    }

    public Cuenta vincularUsuarioCuenta(Long idCuenta, Long idUsuario){
        Cuenta cuenta = cuentaRepository.findById(idCuenta)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        cuenta.getUsuarios().add(usuario);
        cuentaRepository.save(cuenta);
        return cuenta;
    }

    public Cuenta actualizarSaldo(Long idCuenta, Double monto){
        return cuentaRepository.findById(idCuenta)
                .map(cuenta ->{
                    cuenta.setSaldo(cuenta.getSaldo() + monto);
                    return cuentaRepository.save(cuenta);
                }).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    public Cuenta desactivarCuenta(Long idCuenta){
        return cuentaRepository.findById(idCuenta)
                .map(cuenta ->{
                    cuenta.setActiva(false);
                    return cuentaRepository.save(cuenta);
                }).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    public Cuenta reactivarCuenta(Long idCuenta){
        return cuentaRepository.findById(idCuenta)
                .map(cuenta ->{
                    cuenta.setActiva(true);
                    return cuentaRepository.save(cuenta);
                }).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

}
