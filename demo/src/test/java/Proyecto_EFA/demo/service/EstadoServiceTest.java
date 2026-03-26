package Proyecto_EFA.demo.service;

import Proyecto_EFA.demo.model.Estado;
import Proyecto_EFA.demo.repository.EstadoRepository;
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
class EstadoServiceTest {

    @Mock
    private EstadoRepository estadoRepository;

    @InjectMocks
    private EstadoService estadoService;

    private Estado e1;

    @BeforeEach
    void setUp() {
        e1 = new Estado();
        e1.setId(1);
        e1.setNombre("Pendiente");
    }

    @Test
    void findAll_shouldReturnList() {
        when(estadoRepository.findAll()).thenReturn(Arrays.asList(e1));
        var res = estadoService.getAllEstados();
        assertNotNull(res);
        assertEquals(1, res.size());
    }

    @Test
    void getById_whenExists() {
        when(estadoRepository.findById(1)).thenReturn(Optional.of(e1));
        Estado res = estadoService.getEstadoById(1);
        assertNotNull(res);
        assertEquals("Pendiente", res.getNombre());
    }
}
