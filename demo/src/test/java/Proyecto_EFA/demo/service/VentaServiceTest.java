package Proyecto_EFA.demo.service;

import Proyecto_EFA.demo.model.Venta;
import Proyecto_EFA.demo.repository.VentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VentaServiceTest {

    @Mock
    private VentaRepository ventaRepository;

    @InjectMocks
    private VentaService ventaService;

    private Venta v1;

    @BeforeEach
    void setUp() {
        v1 = new Venta();
        v1.setId(1);
        v1.setNumeroVenta("NV-1");
    }

    @Test
    void findAll_shouldReturnList() {
        when(ventaRepository.findAll()).thenReturn(Arrays.asList(v1));
        var result = ventaService.getAllVentas();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(ventaRepository, times(1)).findAll();
    }

    @Test
    void getById_whenExists() {
        when(ventaRepository.findById(1)).thenReturn(Optional.of(v1));
        Venta res = ventaService.getVentaById(1);
        assertNotNull(res);
        assertEquals("NV-1", res.getNumeroVenta());
    }
}//muero
