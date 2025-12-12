package Proyecto_EFA.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import Proyecto_EFA.demo.model.Tallas;
import Proyecto_EFA.demo.service.TallasService;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TallaControllerTest {
    @Mock private TallasService tallasService;
    @InjectMocks private TallaController tallaController;

    @Test void getAllTallas_ShouldReturnAll() {
        Tallas t1 = new Tallas(); t1.setId(1); t1.setNombre("S");
        Tallas t2 = new Tallas(); t2.setId(2); t2.setNombre("M");
        List<Tallas> tallas = Arrays.asList(t1, t2);
        when(tallasService.getAllTallas()).thenReturn(tallas);
        ResponseEntity<List<Tallas>> response = tallaController.getAllTallas();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test void getTallaById_WhenExists_ShouldReturnTalla() {
        Tallas talla = new Tallas(); talla.setId(1); talla.setNombre("M");
        when(tallasService.getTallaById(1)).thenReturn(talla);
        ResponseEntity<Tallas> response = tallaController.getTallaById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("M", response.getBody().getNombre());
    }

    @Test void getTallaById_WhenNotExists_ShouldReturnNotFound() {
        when(tallasService.getTallaById(999)).thenReturn(null);
        ResponseEntity<Tallas> response = tallaController.getTallaById(999);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void createTalla_ShouldReturnCreated() {
        Tallas tallaToCreate = new Tallas(); tallaToCreate.setNombre("XL");
        Tallas created = new Tallas(); created.setId(3); created.setNombre("XL");
        when(tallasService.createTalla(tallaToCreate)).thenReturn(created);
        ResponseEntity<Tallas> response = tallaController.createTalla(tallaToCreate);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(3, response.getBody().getId());
    }

    @Test void updateTalla_WhenExists_ShouldReturnUpdated() {
        Tallas talla = new Tallas(); talla.setId(1); talla.setNombre("L Grande");
        when(tallasService.updateTalla(1, talla)).thenReturn(talla);
        ResponseEntity<Tallas> response = tallaController.updateTalla(1, talla);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("L Grande", response.getBody().getNombre());
    }

    @Test void updateTalla_WhenNotExists_ShouldReturnNotFound() {
        Tallas talla = new Tallas(); talla.setId(999); talla.setNombre("Inexistente");
        when(tallasService.updateTalla(999, talla)).thenReturn(null);
        ResponseEntity<Tallas> response = tallaController.updateTalla(999, talla);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void deleteTalla_ShouldCallService() {
        doNothing().when(tallasService).deleteTalla(1);
        ResponseEntity<Void> response = tallaController.deleteTalla(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(tallasService, times(1)).deleteTalla(1);
    }
}