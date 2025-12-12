package Proyecto_EFA.demo.service;

import Proyecto_EFA.demo.model.Producto;
import Proyecto_EFA.demo.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void getProductoById_whenMissing_returnsNull() {
        when(productoRepository.findById(999)).thenReturn(Optional.empty());
        assertNull(productoService.getProductoById(999));
    }
}
