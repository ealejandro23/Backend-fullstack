package Proyecto_EFA.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import Proyecto_EFA.demo.model.MetodoEnvio;
import Proyecto_EFA.demo.service.MetodoEnvioService;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MetodoEnvioControllerTest {
    @Mock private MetodoEnvioService metodoEnvioService;
    @InjectMocks private MetodoEnvioController metodoEnvioController;

    @Test void getAllMetodosEnvio_ShouldReturnAll() {
        MetodoEnvio me1 = new MetodoEnvio(); me1.setId(1); me1.setNombre("Express");
        MetodoEnvio me2 = new MetodoEnvio(); me2.setId(2); me2.setNombre("Estandar");
        List<MetodoEnvio> metodos = Arrays.asList(me1, me2);
        when(metodoEnvioService.getAllMetodosEnvio()).thenReturn(metodos);
        ResponseEntity<List<MetodoEnvio>> response = metodoEnvioController.getAllMetodosEnvio();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test void getMetodoEnvioById_WhenExists_ShouldReturnMetodo() {
        MetodoEnvio me = new MetodoEnvio(); me.setId(1); me.setNombre("Express");
        when(metodoEnvioService.getMetodoEnvioById(1)).thenReturn(me);
        ResponseEntity<MetodoEnvio> response = metodoEnvioController.getMetodoEnvioById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Express", response.getBody().getNombre());
    }

    @Test void getMetodoEnvioByNombre_WhenExists_ShouldReturnMetodo() {
        MetodoEnvio me = new MetodoEnvio(); me.setId(1); me.setNombre("Express");
        when(metodoEnvioService.getMetodoEnvioByNombre("Express")).thenReturn(me);
        ResponseEntity<MetodoEnvio> response = metodoEnvioController.getMetodoEnvioByNombre("Express");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Express", response.getBody().getNombre());
    }

    @Test void createMetodoEnvio_ShouldReturnCreated() {
        MetodoEnvio meToCreate = new MetodoEnvio(); meToCreate.setNombre("Nuevo Envío");
        MetodoEnvio created = new MetodoEnvio(); created.setId(3); created.setNombre("Nuevo Envío");
        when(metodoEnvioService.createMetodoEnvio(meToCreate)).thenReturn(created);
        ResponseEntity<?> response = metodoEnvioController.createMetodoEnvio(meToCreate);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(3, ((MetodoEnvio) response.getBody()).getId());
    }

    @Test void createMetodoEnvio_WhenError_ShouldReturnBadRequest() {
        MetodoEnvio meToCreate = new MetodoEnvio(); meToCreate.setNombre("Error");
        when(metodoEnvioService.createMetodoEnvio(meToCreate)).thenThrow(new RuntimeException("Error"));
        ResponseEntity<?> response = metodoEnvioController.createMetodoEnvio(meToCreate);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test void updateMetodoEnvio_WhenExists_ShouldReturnUpdated() {
        MetodoEnvio me = new MetodoEnvio(); me.setId(1); me.setNombre("Express Actualizado");
        when(metodoEnvioService.updateMetodoEnvio(1, me)).thenReturn(me);
        ResponseEntity<?> response = metodoEnvioController.updateMetodoEnvio(1, me);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Express Actualizado", ((MetodoEnvio) response.getBody()).getNombre());
    }

    @Test void deleteMetodoEnvio_ShouldCallService() {
        doNothing().when(metodoEnvioService).deleteMetodoEnvio(1);
        ResponseEntity<?> response = metodoEnvioController.deleteMetodoEnvio(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(metodoEnvioService, times(1)).deleteMetodoEnvio(1);
    }
}