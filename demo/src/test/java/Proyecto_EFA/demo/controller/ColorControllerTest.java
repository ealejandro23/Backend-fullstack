package Proyecto_EFA.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import Proyecto_EFA.demo.model.Colores;
import Proyecto_EFA.demo.service.ColoresService;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ColorControllerTest {
    @Mock private ColoresService coloresService;
    @InjectMocks private ColorController colorController;

    @Test void getAllColores_ShouldReturnAll() {
        Colores c1 = new Colores(); c1.setId(1); c1.setNombre("Rojo");
        Colores c2 = new Colores(); c2.setId(2); c2.setNombre("Azul");
        List<Colores> colores = Arrays.asList(c1, c2);
        when(coloresService.getAllColores()).thenReturn(colores);
        ResponseEntity<List<Colores>> response = colorController.getAllColores();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test void getColorById_WhenExists_ShouldReturnColor() {
        Colores color = new Colores(); color.setId(1); color.setNombre("Rojo");
        when(coloresService.getColorById(1)).thenReturn(color);
        ResponseEntity<Colores> response = colorController.getColorById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Rojo", response.getBody().getNombre());
    }

    @Test void getColorById_WhenNotExists_ShouldReturnNotFound() {
        when(coloresService.getColorById(999)).thenReturn(null);
        ResponseEntity<Colores> response = colorController.getColorById(999);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void createColor_ShouldReturnCreated() {
        Colores colorToCreate = new Colores(); colorToCreate.setNombre("Nuevo Color");
        Colores created = new Colores(); created.setId(3); created.setNombre("Nuevo Color");
        when(coloresService.createColor(colorToCreate)).thenReturn(created);
        ResponseEntity<Colores> response = colorController.createColor(colorToCreate);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(3, response.getBody().getId());
    }

    @Test void updateColor_WhenExists_ShouldReturnUpdated() {
        Colores color = new Colores(); color.setId(1); color.setNombre("Rojo Oscuro");
        when(coloresService.updateColor(1, color)).thenReturn(color);
        ResponseEntity<Colores> response = colorController.updateColor(1, color);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Rojo Oscuro", response.getBody().getNombre());
    }

    @Test void updateColor_WhenNotExists_ShouldReturnNotFound() {
        Colores color = new Colores(); color.setId(999); color.setNombre("Inexistente");
        when(coloresService.updateColor(999, color)).thenReturn(null);
        ResponseEntity<Colores> response = colorController.updateColor(999, color);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void deleteColor_ShouldCallService() {
        doNothing().when(coloresService).deleteColor(1);
        ResponseEntity<Void> response = colorController.deleteColor(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(coloresService, times(1)).deleteColor(1);
    }
}