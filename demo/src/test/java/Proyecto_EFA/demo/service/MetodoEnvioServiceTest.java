package Proyecto_EFA.demo.service;

import Proyecto_EFA.demo.model.MetodoEnvio;
import Proyecto_EFA.demo.repository.MetodoEnvioRepository;
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
class MetodoEnvioServiceTest {

    @Mock
    private MetodoEnvioRepository metodoEnvioRepository;

    @InjectMocks
    private MetodoEnvioService metodoEnvioService;

    private MetodoEnvio me1;

    @BeforeEach
    void setUp() {
        me1 = new MetodoEnvio();
        me1.setId(1);
        me1.setNombre("Envio1");
        me1.setPrecio(5.0);
    }

    @Test
    void findAll_shouldReturnList() {
        when(metodoEnvioRepository.findAll()).thenReturn(Arrays.asList(me1));
        var res = metodoEnvioService.getAllMetodosEnvio();
        assertNotNull(res);
        assertEquals(1, res.size());
    }

    @Test
    void getById_whenExists() {
        when(metodoEnvioRepository.findById(1)).thenReturn(Optional.of(me1));
        MetodoEnvio res = metodoEnvioService.getMetodoEnvioById(1);
        assertNotNull(res);
        assertEquals("Envio1", res.getNombre());
    }
}
