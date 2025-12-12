package Proyecto_EFA.demo.service;

import Proyecto_EFA.demo.model.Materiales;
import Proyecto_EFA.demo.repository.MaterialesRepository;
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
class MaterialesServiceTest {

    @Mock
    private MaterialesRepository materialesRepository;

    @InjectMocks
    private MaterialesService materialesService;

    private Materiales m1;

    @BeforeEach
    void setUp() {
        m1 = new Materiales();
        m1.setId(1);
        m1.setNombre("Mat1");
    }

    @Test
    void findAll_shouldReturnList() {
        when(materialesRepository.findAll()).thenReturn(Arrays.asList(m1));
        var res = materialesService.getAllMateriales();
        assertNotNull(res);
        assertEquals(1, res.size());
    }

    @Test
    void getById_whenExists() {
        when(materialesRepository.findById(1)).thenReturn(Optional.of(m1));
        Materiales res = materialesService.getMaterialById(1);
        assertNotNull(res);
        assertEquals("Mat1", res.getNombre());
    }
}
