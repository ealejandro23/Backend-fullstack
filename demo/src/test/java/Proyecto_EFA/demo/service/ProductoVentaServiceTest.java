package Proyecto_EFA.demo.service;

import Proyecto_EFA.demo.model.ProductoVenta;
import Proyecto_EFA.demo.repository.ProductoVentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoVentaServiceTest {

    @Mock
    private ProductoVentaRepository productoVentaRepository;

    @InjectMocks
    private ProductoVentaService productoVentaService;

    private ProductoVenta pv1;

    @BeforeEach
    void setUp() {
        pv1 = new ProductoVenta();
        pv1.setId(1L);
        pv1.setCantidad(2);
    }

    @Test
    void findAll_shouldReturnList() {
        when(productoVentaRepository.findAll()).thenReturn(Arrays.asList(pv1));
        var res = productoVentaService.getAllProductoVentas();
        assertNotNull(res);
        assertEquals(1, res.size());
    }

    @Test
    void getById_whenExists() {
        when(productoVentaRepository.findById(any())).thenReturn(Optional.of(pv1));
        var res = productoVentaService.getProductoVentaById(1);
        assertTrue(res.isPresent());
        assertEquals(2, res.get().getCantidad());
    }
}
