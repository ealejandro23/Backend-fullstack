package Proyecto_EFA.demo.service;

import Proyecto_EFA.demo.model.Colores;
import Proyecto_EFA.demo.repository.ColoresRepository;
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
class ColoresServiceTest {

    @Mock
    private ColoresRepository coloresRepository;

    @InjectMocks
    private ColoresService coloresService;

    private Colores c1;

    @BeforeEach
    void setUp() {
        c1 = new Colores();
        c1.setId(1);
        c1.setNombre("C1");
    }

    @Test
    void findAll_shouldReturnList() {
        when(coloresRepository.findAll()).thenReturn(Arrays.asList(c1));
        var res = coloresService.getAllColores();
        assertNotNull(res);
        assertEquals(1, res.size());
    }

    @Test
    void getById_whenExists() {
        when(coloresRepository.findById(1)).thenReturn(Optional.of(c1));
        Colores res = coloresService.getColorById(1);
        assertNotNull(res);
        assertEquals("C1", res.getNombre());
    }
}
