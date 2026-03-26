package Proyecto_EFA.demo.service;

import Proyecto_EFA.demo.model.Tallas;
import Proyecto_EFA.demo.repository.TallasRepository;
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
class TallasServiceTest {

    @Mock
    private TallasRepository tallasRepository;

    @InjectMocks
    private TallasService tallasService;

    private Tallas t1;

    @BeforeEach
    void setUp() {
        t1 = new Tallas();
        t1.setId(1);
        t1.setNombre("T1");
    }

    @Test
    void findAll_shouldReturnList() {
        when(tallasRepository.findAll()).thenReturn(Arrays.asList(t1));
        var res = tallasService.getAllTallas();
        assertNotNull(res);
        assertEquals(1, res.size());
    }

    @Test
    void getById_whenExists() {
        when(tallasRepository.findById(1)).thenReturn(Optional.of(t1));
        Tallas res = tallasService.getTallaById(1);
        assertNotNull(res);
        assertEquals("T1", res.getNombre());
    }
}
