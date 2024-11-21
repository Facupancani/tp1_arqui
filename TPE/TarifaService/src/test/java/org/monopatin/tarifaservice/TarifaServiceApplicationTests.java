package org.monopatin.tarifaservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.monopatin.tarifaservice.dto.TarifaRequestDTO;
import org.monopatin.tarifaservice.model.Tarifa;
import org.monopatin.tarifaservice.repository.TarifaRepository;
import org.monopatin.tarifaservice.service.TarifaService;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TarifaServiceApplicationTests {


    @Mock
    private TarifaRepository tarifaRepository;

    @InjectMocks
    private TarifaService tarifaService;

    @Test
    void shouldReturnLatestTarifa() {
        // Mock de datos
        Tarifa mockTarifa = new Tarifa(1.5, 0.5, LocalDate.now());
        when(tarifaRepository.findLatestTarifa()).thenReturn(mockTarifa);

        // Llamada al método
        Tarifa result = tarifaService.getTarifa();

        // Verificaciones
        assertNotNull(result);
        assertEquals(1.5, result.getTarifaPorMinuto());
        assertEquals(0.5, result.getTarifaExtra());
    }

    @Test
    void shouldUpdateTarifaCorrectly() {
        // Mock de datos
        TarifaRequestDTO requestDTO = new TarifaRequestDTO();
        requestDTO.setTarifaPorMinuto(2.0);
        requestDTO.setTarifaExtra(1.0);
        requestDTO.setFechaInicioVigencia(LocalDate.now().plusDays(1));

        Tarifa tarifaAnterior = new Tarifa(1.5, 0.5, LocalDate.now().minusDays(10));
        when(tarifaRepository.findTopByFechaInicioVigenciaLessThanEqualOrderByFechaInicioVigenciaDesc(LocalDate.now()))
                .thenReturn(tarifaAnterior);

        Tarifa nuevaTarifa = new Tarifa(2.0, 1.0, LocalDate.now().plusDays(1));
        when(tarifaRepository.save(any(Tarifa.class))).thenReturn(nuevaTarifa);

        // Llamada al método
        Tarifa result = tarifaService.actualizarTarifa(requestDTO);

        // Verificaciones
        assertNotNull(result);
        assertEquals(2.0, result.getTarifaPorMinuto());
        assertEquals(1.0, result.getTarifaExtra());

        // Validar que se llamó 2 veces al método save
        ArgumentCaptor<Tarifa> tarifaCaptor = ArgumentCaptor.forClass(Tarifa.class);
        verify(tarifaRepository, times(2)).save(tarifaCaptor.capture());

        // Validar los argumentos de cada llamada
        List<Tarifa> tarifasGuardadas = tarifaCaptor.getAllValues();
        Tarifa tarifaGuardadaAnterior = tarifasGuardadas.get(0);
        Tarifa tarifaGuardadaNueva = tarifasGuardadas.get(1);

        // Validar tarifa anterior
        assertEquals(LocalDate.now().plusDays(1).minusDays(1), tarifaGuardadaAnterior.getFechaFinVigencia());

        // Validar nueva tarifa
        assertEquals(2.0, tarifaGuardadaNueva.getTarifaPorMinuto());
        assertEquals(1.0, tarifaGuardadaNueva.getTarifaExtra());
    }

}
