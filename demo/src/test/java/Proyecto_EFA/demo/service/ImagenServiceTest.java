package Proyecto_EFA.demo.service;

import Proyecto_EFA.demo.model.Imagen;
import Proyecto_EFA.demo.repository.ImagenRepository;
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
class ImagenServiceTest {

    @Mock
    private ImagenRepository imagenRepository;

    @InjectMocks
    private ImagenService imagenService;

    private Imagen i1;

    @BeforeEach
    void setUp() {
        i1 = new Imagen();
        i1.setId(1);
        i1.setUrl("http://x");
    }

    @Test
    void findById_whenExists() {
        when(imagenRepository.findById(1)).thenReturn(Optional.of(i1));
        Imagen res = imagenService.getImagenById(1);
        assertNotNull(res);
        assertEquals("http://x", res.getUrl());
    }
}
