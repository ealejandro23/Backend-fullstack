package Proyecto_EFA.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import Proyecto_EFA.demo.model.Producto;
import Proyecto_EFA.demo.model.Imagen;
import Proyecto_EFA.demo.dto.ProductoCreationDTO;
import Proyecto_EFA.demo.service.ProductoService;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {
    @Mock private ProductoService productoService;
    @InjectMocks private ProductoController productoController;

    @Test void getAllProductos_WithoutFilters_ShouldReturnAll() {
        Producto p1 = new Producto(); p1.setId(1); p1.setNombre("Producto1");
        Producto p2 = new Producto(); p2.setId(2); p2.setNombre("Producto2");
        List<Producto> productos = Arrays.asList(p1, p2);
        when(productoService.getAllProductos()).thenReturn(productos);
        ResponseEntity<List<Producto>> response = productoController.getProductosFiltrados(null, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test void getProductosFiltrados_WithFilters_ShouldReturnFiltered() {
        Producto p1 = new Producto(); p1.setId(1); p1.setNombre("Producto1");
        List<Producto> productos = Arrays.asList(p1);
        when(productoService.findProductosByFilters("Ropa", "Hombre")).thenReturn(productos);
        ResponseEntity<List<Producto>> response = productoController.getProductosFiltrados("Ropa", "Hombre");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test void getProductoById_WhenExists_ShouldReturnProducto() {
        Producto producto = new Producto(); producto.setId(1); producto.setNombre("Camisa");
        when(productoService.getProductoById(1)).thenReturn(producto);
        ResponseEntity<Producto> response = productoController.getProductoById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Camisa", response.getBody().getNombre());
    }

    @Test void getProductoById_WhenNotExists_ShouldReturnNotFound() {
        when(productoService.getProductoById(999)).thenReturn(null);
        ResponseEntity<Producto> response = productoController.getProductoById(999);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void createProducto_ShouldReturnCreated() {
        ProductoCreationDTO dto = new ProductoCreationDTO();
        Producto created = new Producto(); created.setId(1); created.setNombre("Nuevo Producto");
        when(productoService.createProducto(dto)).thenReturn(created);
        ResponseEntity<Producto> response = productoController.createProducto(dto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, response.getBody().getId());
    }

    @Test void updateProducto_WhenExists_ShouldReturnUpdated() {
        Producto producto = new Producto(); producto.setId(1); producto.setNombre("Actualizado");
        when(productoService.updateProducto(1, producto)).thenReturn(producto);
        ResponseEntity<Producto> response = productoController.updateProducto(1, producto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Actualizado", response.getBody().getNombre());
    }

    @Test void updateProducto_WhenNotExists_ShouldReturnNotFound() {
        Producto producto = new Producto(); producto.setId(999); producto.setNombre("Inexistente");
        when(productoService.updateProducto(999, producto)).thenReturn(null);
        ResponseEntity<Producto> response = productoController.updateProducto(999, producto);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void deleteProducto_ShouldCallService() {
        doNothing().when(productoService).deleteProducto(1);
        ResponseEntity<Void> response = productoController.deleteProducto(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productoService, times(1)).deleteProducto(1);
    }

    @Test void addImageToProducto_ShouldReturnCreated() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test".getBytes());
        Imagen imagen = new Imagen(); imagen.setId(1); imagen.setUrl("test.jpg");
        when(productoService.addImageToProducto(1, file)).thenReturn(imagen);
        ResponseEntity<?> response = productoController.addImageToProducto(1, file);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test void getProductosByMarca_ShouldReturnList() {
        Producto p1 = new Producto(); p1.setId(1); p1.setNombre("Producto1");
        List<Producto> productos = Arrays.asList(p1);
        when(productoService.getProductosByMarca(1)).thenReturn(productos);
        ResponseEntity<List<Producto>> response = productoController.getProductosByMarca(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test void getTop10MostExpensiveProducts_ShouldReturnList() {
        Producto p1 = new Producto(); p1.setId(1); p1.setNombre("Producto Caro");
        List<Producto> productos = Arrays.asList(p1);
        when(productoService.getTop10MostExpensiveProducts()).thenReturn(productos);
        ResponseEntity<List<Producto>> response = productoController.getTop10MostExpensiveProducts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }
}