package Proyecto_EFA.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import Proyecto_EFA.demo.dto.VentaRequest;
import Proyecto_EFA.demo.dto.ItemVentaRequest;
import Proyecto_EFA.demo.model.*;
import Proyecto_EFA.demo.service.*;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VentaControllerTest {
    @Mock private VentaService ventaService;
    @Mock private UsuarioService usuarioService;
    @Mock private EstadoService estadoService;
    @Mock private MetodoPagoService metodoPagoService;
    @Mock private MetodoEnvioService metodoEnvioService;
    @Mock private ProductoService productoService;
    @InjectMocks private VentaController ventaController;

    @Test void createVenta_WithValidData_ShouldReturnCreated() {
        VentaRequest ventaRequest = new VentaRequest();
        ventaRequest.setUsuarioId(1);
        ventaRequest.setEstadoId(1);
        ventaRequest.setMetodoPagoId(1);
        ventaRequest.setMetodoEnvioId(1);
        
        ItemVentaRequest item = new ItemVentaRequest();
        item.setProductoId(1);
        item.setCantidad(2);
        item.setPrecioUnitario(100.0);
        ventaRequest.setItems(Arrays.asList(item));

        Usuario usuario = new Usuario(); usuario.setId(1);
        Estado estado = new Estado(); estado.setId(1);
        MetodoPago metodoPago = new MetodoPago(); metodoPago.setId(1);
        MetodoEnvio metodoEnvio = new MetodoEnvio(); metodoEnvio.setId(1);
        Producto producto = new Producto(); producto.setId(1);
        Venta venta = new Venta(); venta.setId(1);

        when(usuarioService.getUsuarioById(1)).thenReturn(usuario);
        when(estadoService.getEstadoById(1)).thenReturn(estado);
        when(metodoPagoService.getMetodoPagoById(1)).thenReturn(metodoPago);
        when(metodoEnvioService.getMetodoEnvioById(1)).thenReturn(metodoEnvio);
        when(productoService.getProductoById(1)).thenReturn(producto);
        when(ventaService.createVenta(any(Venta.class))).thenReturn(venta);

        ResponseEntity<?> response = ventaController.createVenta(ventaRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, ((Venta) response.getBody()).getId());
    }

    @Test void createVenta_WithoutUserId_ShouldReturnBadRequest() {
        VentaRequest ventaRequest = new VentaRequest();
        ventaRequest.setUsuarioId(null);
        ventaRequest.setEstadoId(1);
        ventaRequest.setMetodoPagoId(1);
        ventaRequest.setMetodoEnvioId(1);

        ResponseEntity<?> response = ventaController.createVenta(ventaRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("ERROR"));
    }

    @Test void createVenta_WithoutItems_ShouldReturnBadRequest() {
        VentaRequest ventaRequest = new VentaRequest();
        ventaRequest.setUsuarioId(1);
        ventaRequest.setEstadoId(1);
        ventaRequest.setMetodoPagoId(1);
        ventaRequest.setMetodoEnvioId(1);
        ventaRequest.setItems(Arrays.asList());

        ResponseEntity<?> response = ventaController.createVenta(ventaRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test void getAllVentas_ShouldReturnAll() {
        Venta v1 = new Venta(); v1.setId(1);
        Venta v2 = new Venta(); v2.setId(2);
        List<Venta> ventas = Arrays.asList(v1, v2);
        when(ventaService.getAllVentas()).thenReturn(ventas);

        ResponseEntity<List<Venta>> response = ventaController.getAllVentas();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test void getVentaById_WhenExists_ShouldReturnVenta() {
        Venta venta = new Venta(); venta.setId(1);
        when(ventaService.getVentaById(1)).thenReturn(venta);

        ResponseEntity<Venta> response = ventaController.getVentaById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getId());
    }

    @Test void getVentaById_WhenNotExists_ShouldReturnNotFound() {
        when(ventaService.getVentaById(999)).thenReturn(null);
        ResponseEntity<Venta> response = ventaController.getVentaById(999);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void updateVenta_WhenExists_ShouldReturnUpdated() {
        Venta venta = new Venta(); venta.setId(1);
        when(ventaService.updateVenta(1, venta)).thenReturn(venta);
        ResponseEntity<Venta> response = ventaController.updateVenta(1, venta);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getId());
    }

    @Test void deleteVenta_ShouldCallService() {
        doNothing().when(ventaService).deleteVenta(1);
        ResponseEntity<Void> response = ventaController.deleteVenta(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(ventaService, times(1)).deleteVenta(1);
    }

    @Test void getVentasByUsuario_ShouldReturnList() {
        Venta venta = new Venta(); venta.setId(1);
        List<Venta> ventas = Arrays.asList(venta);
        when(ventaService.getVentasByUsuario(1)).thenReturn(ventas);
        ResponseEntity<List<Venta>> response = ventaController.getVentasByUsuario(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test void getVentaByNumeroVenta_WhenExists_ShouldReturnVenta() {
        Venta venta = new Venta(); venta.setId(1); venta.setNumeroVenta("VEN-123");
        when(ventaService.getVentaByNumeroVenta("VEN-123")).thenReturn(venta);
        ResponseEntity<Venta> response = ventaController.getVentaByNumeroVenta("VEN-123");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("VEN-123", response.getBody().getNumeroVenta());
    }
}