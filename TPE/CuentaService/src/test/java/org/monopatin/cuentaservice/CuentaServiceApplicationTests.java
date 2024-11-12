package org.monopatin.cuentaservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.monopatin.cuentaservice.entities.Cuenta;
import org.monopatin.cuentaservice.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class CuentaServiceApplicationTests {

    @Autowired
    private CuentaService cuentaService;

    @Test
    void crearCuentaTest() {
        // Crear una cuenta de prueba
        Cuenta cuenta = new Cuenta();
        cuenta.setNombreTitular("Juan Perez");
        cuenta.setEmail("juan.perez@example.com");
        cuenta.setFechaAlta(LocalDate.now());

        // Guardar la cuenta en la base de datos
        Cuenta nuevaCuenta = cuentaService.crearCuenta(cuenta);

        // Verificar que la cuenta se guardó correctamente
        Assertions.assertNotNull(nuevaCuenta.getIdCuenta(), "La cuenta no se creó correctamente.");
        Assertions.assertEquals("Juan Perez", nuevaCuenta.getNombreTitular());
        Assertions.assertEquals("juan.perez@example.com", nuevaCuenta.getEmail());
        Assertions.assertEquals(0.0, nuevaCuenta.getSaldo());
    }
}
