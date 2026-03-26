package Proyecto_EFA.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import Proyecto_EFA.demo.model.ProductoVenta;
import Proyecto_EFA.demo.service.ProductoVentaService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoVentaControllerTest {
    @Mock private ProductoVentaService productoVentaService;
    @InjectMocks private ProductoVentaController productoVentaController;

    @Test void getAllProductoVentas_ShouldReturnAll() {
        ProductoVenta pv1 = new ProductoVenta(); pv1.setId(1L);
        ProductoVenta pv2 = new ProductoVenta(); pv2.setId(2L);
        List<ProductoVenta> list = Arrays.asList(pv1, pv2);
        when(productoVentaService.getAllProductoVentas()).thenReturn(list);
        ResponseEntity<List<ProductoVenta>> response = productoVentaController.getAllProductoVentas();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test void getProductoVentaById_WhenExists_ShouldReturnProductoVenta() {
        ProductoVenta pv = new ProductoVenta(); pv.setId(1L);
        when(productoVentaService.getProductoVentaById(1)).thenReturn(Optional.of(pv));
        ResponseEntity<ProductoVenta> response = productoVentaController.getProductoVentaById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId().longValue());
    }

    @Test void getProductoVentaById_WhenNotExists_ShouldReturnNotFound() {
        when(productoVentaService.getProductoVentaById(999)).thenReturn(Optional.empty());
        ResponseEntity<ProductoVenta> response = productoVentaController.getProductoVentaById(999);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void createProductoVenta_ShouldReturnCreated() {
        ProductoVenta pvToCreate = new ProductoVenta();
        ProductoVenta created = new ProductoVenta(); created.setId(1L);
        when(productoVentaService.createProductoVenta(pvToCreate)).thenReturn(created);
        ResponseEntity<ProductoVenta> response = productoVentaController.createProductoVenta(pvToCreate);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId().longValue());
    }

    @Test void updateProductoVenta_WhenExists_ShouldReturnUpdated() {
        ProductoVenta pv = new ProductoVenta(); pv.setId(1L);
        when(productoVentaService.updateProductoVenta(1, pv)).thenReturn(pv);
        ResponseEntity<ProductoVenta> response = productoVentaController.updateProductoVenta(1, pv);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId().longValue());
    }

    @Test void updateProductoVenta_WhenNotExists_ShouldReturnNotFound() {
        ProductoVenta pv = new ProductoVenta(); pv.setId(999L);
        when(productoVentaService.updateProductoVenta(999, pv)).thenReturn(null);
        ResponseEntity<ProductoVenta> response = productoVentaController.updateProductoVenta(999, pv);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void deleteProductoVenta_ShouldReturnOk() {
        doNothing().when(productoVentaService).deleteProductoVenta(1);
        ResponseEntity<Void> response = productoVentaController.deleteProductoVenta(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productoVentaService, times(1)).deleteProductoVenta(1);
    }

    @Test void getProductoVentasByVenta_ShouldReturnList() {
        ProductoVenta pv = new ProductoVenta(); pv.setId(1L);
        List<ProductoVenta> list = Arrays.asList(pv);
        when(productoVentaService.getProductoVentasByVenta(1)).thenReturn(list);
        ResponseEntity<List<ProductoVenta>> response = productoVentaController.getProductoVentasByVenta(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test void getTotalQuantitySoldByProducto_ShouldReturnInteger() {
        when(productoVentaService.getTotalQuantitySoldByProducto(1)).thenReturn(50);
        ResponseEntity<Integer> response = productoVentaController.getTotalQuantitySoldByProducto(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(50, response.getBody());
    }

    @Test void getTopSellingProducts_ShouldReturnList() {
        Object[] topProduct = new Object[]{"Producto1", 100L};
        List<Object[]> topProducts = java.util.Collections.singletonList(topProduct);
        when(productoVentaService.getTopSellingProducts(10)).thenReturn(topProducts);
        ResponseEntity<List<Object[]>> response = productoVentaController.getTopSellingProducts(10);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }
}
