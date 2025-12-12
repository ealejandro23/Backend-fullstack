package Proyecto_EFA.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import Proyecto_EFA.demo.model.Estado;
import Proyecto_EFA.demo.service.EstadoService;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstadoControllerTest {
    @Mock private EstadoService estadoService;
    @InjectMocks private EstadoController estadoController;

    @Test void getAllEstados_ShouldReturnAll() {
        Estado estado1 = new Estado(); estado1.setId(1); estado1.setNombre("Pendiente");
        Estado estado2 = new Estado(); estado2.setId(2); estado2.setNombre("Entregado");
        List<Estado> estados = Arrays.asList(estado1, estado2);
        when(estadoService.getAllEstados()).thenReturn(estados);
        ResponseEntity<List<Estado>> response = estadoController.getAllEstados();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test void getEstadoById_WhenExists_ShouldReturnEstado() {
        Estado estado = new Estado(); estado.setId(1); estado.setNombre("Pendiente");
        when(estadoService.getEstadoById(1)).thenReturn(estado);
        ResponseEntity<Estado> response = estadoController.getEstadoById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Pendiente", response.getBody().getNombre());
    }

    @Test void getEstadoById_WhenNotExists_ShouldReturnNotFound() {
        when(estadoService.getEstadoById(999)).thenReturn(null);
        ResponseEntity<Estado> response = estadoController.getEstadoById(999);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void createEstado_ShouldReturnCreated() {
        Estado estadoToCreate = new Estado(); estadoToCreate.setNombre("Nuevo Estado");
        Estado created = new Estado(); created.setId(3); created.setNombre("Nuevo Estado");
        when(estadoService.createEstado(estadoToCreate)).thenReturn(created);
        ResponseEntity<Estado> response = estadoController.createEstado(estadoToCreate);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(3, response.getBody().getId());
    }

    @Test void updateEstado_WhenExists_ShouldReturnUpdated() {
        Estado estado = new Estado(); estado.setId(1); estado.setNombre("Actualizado");
        when(estadoService.updateEstado(1, estado)).thenReturn(estado);
        ResponseEntity<Estado> response = estadoController.updateEstado(1, estado);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Actualizado", response.getBody().getNombre());
    }

    @Test void updateEstado_WhenNotExists_ShouldReturnNotFound() {
        Estado estado = new Estado(); estado.setId(999); estado.setNombre("Inexistente");
        when(estadoService.updateEstado(999, estado)).thenReturn(null);
        ResponseEntity<Estado> response = estadoController.updateEstado(999, estado);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void deleteEstado_ShouldCallService() {
        doNothing().when(estadoService).deleteEstado(1);
        ResponseEntity<Void> response = estadoController.deleteEstado(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(estadoService, times(1)).deleteEstado(1);
    }
}