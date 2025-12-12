package Proyecto_EFA.demo.service;

import Proyecto_EFA.demo.model.Usuario;
import Proyecto_EFA.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void findById_notFound_returnsNull() {
        when(usuarioRepository.findById(999)).thenReturn(Optional.empty());
        assertNull(usuarioService.findById(999));
    }
}
