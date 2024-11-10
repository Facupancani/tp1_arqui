package org.monopatin.adminservice.services;

import org.monopatin.adminservice.dto.CuentaDTO;
import org.monopatin.adminservice.dto.EnOperacionDTO;
import org.monopatin.adminservice.dto.MonopatinDTO;
import org.monopatin.adminservice.dto.ViajeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String CUENTA_SERVICE_URL = "http://localhost:8002/cuentas";
    private static final String MONOPATIN_SERVICE_URL = "http://localhost:8001/monopatines";
    private static final String VIAJE_SERVICE_URL = "http://localhost:8003/viajes";

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

    public List<MonopatinDTO> obtenerMonopatinesConViajes(int anio, Long cantViajes){

        String viajesUrl = VIAJE_SERVICE_URL + "/monopatines/mas-viajes?anio=" + anio + "&cantViajes=" + cantViajes;
        ResponseEntity<MonopatinDTO[]> response = restTemplate.getForEntity(viajesUrl, MonopatinDTO[].class);
        List<MonopatinDTO> monopatines = Arrays.asList(response.getBody());

        return monopatines;
    }

    public EnOperacionDTO obtenerEstadoDeMonopatines(){

        ResponseEntity<MonopatinDTO[]> response = restTemplate.getForEntity(MONOPATIN_SERVICE_URL, MonopatinDTO[].class);
        List<MonopatinDTO> monopatines = Arrays.asList(response.getBody());

        Long monopatinesEnOperacion = 0L;
        Long monopatinesEnMantenimiento = 0L;

        for(MonopatinDTO m : monopatines){
            if ((m.getEstado().equals("disponible") || m.getEstado().equals("en_uso")) && !m.getEnMantenimiento()){
                monopatinesEnOperacion++;

            }else if (m.getEstado().equals("en_mantenimiento") && m.getEnMantenimiento()){
                monopatinesEnMantenimiento++;
            }
        }

        return new EnOperacionDTO(monopatinesEnOperacion, monopatinesEnMantenimiento);

    }

}
