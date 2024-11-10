package org.monopatin.adminservice.services;

import org.monopatin.adminservice.dto.CuentaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String CUENTA_SERVICE_URL = "http://localhost:8002/cuentas";

    public void anularCuenta(Long idCuenta){
        String url = CUENTA_SERVICE_URL + "/" + idCuenta + "/desactivar";
        ResponseEntity<Void> response = restTemplate.patchForObject(url, null, ResponseEntity.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("No se pudo anular la cuenta ID: " + idCuenta);
        }
    }

    public void reactivarCuenta(Long idCuenta){
        String url = CUENTA_SERVICE_URL + "/" + idCuenta + "/reactivar";
        ResponseEntity<Void> response = restTemplate.patchForObject(url, null, ResponseEntity.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("No se pudo reactivar la cuenta ID: " + idCuenta);
        }
    }

}
