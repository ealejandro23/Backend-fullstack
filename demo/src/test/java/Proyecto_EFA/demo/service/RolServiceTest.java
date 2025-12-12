package Proyecto_EFA.demo.service;

import Proyecto_EFA.demo.model.Rol;
import Proyecto_EFA.demo.repository.RolRepository;
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
class RolServiceTest {

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private RolService rolService;

    private Rol r1;

    @BeforeEach
    void setUp() {
        r1 = new Rol();
        r1.setId(1);
        r1.setNombre("ADMIN");
    }

    @Test
    void findAll_shouldReturnList() {
        when(rolRepository.findAll()).thenReturn(Arrays.asList(r1));
        var res = rolService.getAllRoles();
        assertNotNull(res);
        assertEquals(1, res.size());
    }

    @Test
    void getById_whenExists() {
        when(rolRepository.findById(1)).thenReturn(Optional.of(r1));
        Rol res = rolService.getRolById(1);
        assertNotNull(res);
        assertEquals("ADMIN", res.getNombre());
    }
}
