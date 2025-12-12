package Proyecto_EFA.demo.service;

import Proyecto_EFA.demo.model.MetodoPago;
import Proyecto_EFA.demo.repository.MetodoPagoRepository;
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
class MetodoPagoServiceTest {

    @Mock
    private MetodoPagoRepository metodoPagoRepository;

    @InjectMocks
    private MetodoPagoService metodoPagoService;

    private MetodoPago m1;

    @BeforeEach
    void setUp() {
        m1 = new MetodoPago();
        m1.setId(1);
        m1.setNombre("Pago1");
    }

    @Test
    void findAll_shouldReturnList() {
        when(metodoPagoRepository.findAll()).thenReturn(Arrays.asList(m1));
        var res = metodoPagoService.getAllMetodosPago();
        assertNotNull(res);
        assertEquals(1, res.size());
    }

    @Test
    void getById_whenExists() {
        when(metodoPagoRepository.findById(1)).thenReturn(Optional.of(m1));
        MetodoPago res = metodoPagoService.getMetodoPagoById(1);
        assertNotNull(res);
        assertEquals("Pago1", res.getNombre());
    }
}
