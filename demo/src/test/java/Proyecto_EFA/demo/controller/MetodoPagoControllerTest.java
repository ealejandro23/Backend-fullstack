package Proyecto_EFA.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import Proyecto_EFA.demo.model.MetodoPago;
import Proyecto_EFA.demo.service.MetodoPagoService;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MetodoPagoControllerTest {
    @Mock private MetodoPagoService metodoPagoService;
    @InjectMocks private MetodoPagoController metodoPagoController;

    @Test void getAllMetodosPago_ShouldReturnAll() {
        MetodoPago mp1 = new MetodoPago(); mp1.setId(1); mp1.setNombre("Tarjeta");
        MetodoPago mp2 = new MetodoPago(); mp2.setId(2); mp2.setNombre("Efectivo");
        List<MetodoPago> metodos = Arrays.asList(mp1, mp2);
        when(metodoPagoService.getAllMetodosPago()).thenReturn(metodos);
        ResponseEntity<List<MetodoPago>> response = metodoPagoController.getAllMetodosPago();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test void getMetodoPagoById_WhenExists_ShouldReturnMetodo() {
        MetodoPago mp = new MetodoPago(); mp.setId(1); mp.setNombre("Tarjeta");
        when(metodoPagoService.getMetodoPagoById(1)).thenReturn(mp);
        ResponseEntity<MetodoPago> response = metodoPagoController.getMetodoPagoById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tarjeta", response.getBody().getNombre());
    }

    @Test void getMetodoPagoById_WhenNotExists_ShouldReturnNotFound() {
        when(metodoPagoService.getMetodoPagoById(999)).thenReturn(null);
        ResponseEntity<MetodoPago> response = metodoPagoController.getMetodoPagoById(999);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void getMetodoPagoByNombre_WhenExists_ShouldReturnMetodo() {
        MetodoPago mp = new MetodoPago(); mp.setId(1); mp.setNombre("Tarjeta");
        when(metodoPagoService.getMetodoPagoByNombre("Tarjeta")).thenReturn(mp);
        ResponseEntity<MetodoPago> response = metodoPagoController.getMetodoPagoByNombre("Tarjeta");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tarjeta", response.getBody().getNombre());
    }

    @Test void createMetodoPago_ShouldReturnCreated() {
        MetodoPago mpToCreate = new MetodoPago(); mpToCreate.setNombre("Nuevo Método");
        MetodoPago created = new MetodoPago(); created.setId(3); created.setNombre("Nuevo Método");
        when(metodoPagoService.createMetodoPago(mpToCreate)).thenReturn(created);
        ResponseEntity<?> response = metodoPagoController.createMetodoPago(mpToCreate);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(3, ((MetodoPago) response.getBody()).getId());
    }

    @Test void createMetodoPago_WhenError_ShouldReturnBadRequest() {
        MetodoPago mpToCreate = new MetodoPago(); mpToCreate.setNombre("Error");
        when(metodoPagoService.createMetodoPago(mpToCreate)).thenThrow(new RuntimeException("Error de validación"));
        ResponseEntity<?> response = metodoPagoController.createMetodoPago(mpToCreate);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test void updateMetodoPago_WhenExists_ShouldReturnUpdated() {
        MetodoPago mp = new MetodoPago(); mp.setId(1); mp.setNombre("Tarjeta Actualizada");
        when(metodoPagoService.updateMetodoPago(1, mp)).thenReturn(mp);
        ResponseEntity<?> response = metodoPagoController.updateMetodoPago(1, mp);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tarjeta Actualizada", ((MetodoPago) response.getBody()).getNombre());
    }

    @Test void deleteMetodoPago_ShouldCallService() {
        doNothing().when(metodoPagoService).deleteMetodoPago(1);
        ResponseEntity<?> response = metodoPagoController.deleteMetodoPago(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(metodoPagoService, times(1)).deleteMetodoPago(1);
    }
}