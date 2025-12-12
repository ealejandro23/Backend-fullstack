package Proyecto_EFA.demo.service;

import Proyecto_EFA.demo.model.Marca;
import Proyecto_EFA.demo.repository.MarcaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MarcaServiceTest {

    @Mock
    private MarcaRepository marcaRepository;

    @InjectMocks
    private MarcaService marcaService;

    private Marca m1;

    @BeforeEach
    void setUp() {
        m1 = new Marca();
        m1.setId(1);
        m1.setNombre("M1");
    }

    @Test
    void findById_whenExists() {
        when(marcaRepository.findById(1)).thenReturn(Optional.of(m1));
        Marca res = marcaService.getMarcaById(1);
        assertNotNull(res);
        assertEquals("M1", res.getNombre());
    }
}
