package Proyecto_EFA.demo.service;

import Proyecto_EFA.demo.model.Categorias;
import Proyecto_EFA.demo.repository.CategoriasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriasServiceTest {

    @Mock
    private CategoriasRepository categoriasRepository;

    @InjectMocks
    private CategoriasService categoriasService;

    private Categorias c1;

    @BeforeEach
    void setUp() {
        c1 = new Categorias();
        c1.setId(1);
        c1.setNombre("S1");
    }

    @Test
    void findAll_shouldReturnList() {
        when(categoriasRepository.findAll()).thenReturn(Arrays.asList(c1));
        List<Categorias> result = categoriasService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(categoriasRepository, times(1)).findAll();
    }

    @Test
    void findById_whenExists() {
        when(categoriasRepository.findById(1)).thenReturn(Optional.of(c1));
        Categorias result = categoriasService.findById(1);
        assertNotNull(result);
        assertEquals("S1", result.getNombre());
    }

    @Test
    void save_shouldSaveAndReturn() {
        when(categoriasRepository.save(any(Categorias.class))).thenReturn(c1);
        Categorias toSave = new Categorias();
        toSave.setNombre("New");
        Categorias saved = categoriasService.save(toSave);
        assertEquals(c1.getNombre(), saved.getNombre());
        verify(categoriasRepository, times(1)).save(toSave);
    }
}
