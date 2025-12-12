package Proyecto_EFA.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import Proyecto_EFA.demo.model.Imagen;
import Proyecto_EFA.demo.service.ImagenService;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImagenControllerTest {
    @Mock private ImagenService imagenService;
    @InjectMocks private ImagenController imagenController;

    @Test void getAllImagenes_ShouldReturnAll() {
        Imagen i1 = new Imagen(); i1.setId(1); i1.setUrl("imagen1.jpg");
        Imagen i2 = new Imagen(); i2.setId(2); i2.setUrl("imagen2.jpg");
        List<Imagen> imagenes = Arrays.asList(i1, i2);
        when(imagenService.getAllImagenes()).thenReturn(imagenes);
        ResponseEntity<List<Imagen>> response = imagenController.getAllImagenes();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test void getImagenById_WhenExists_ShouldReturnImagen() {
        Imagen imagen = new Imagen(); imagen.setId(1); imagen.setUrl("imagen1.jpg");
        when(imagenService.getImagenById(1)).thenReturn(imagen);
        ResponseEntity<Imagen> response = imagenController.getImagenById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("imagen1.jpg", response.getBody().getUrl());
    }

    @Test void getImagenById_WhenNotExists_ShouldReturnNotFound() {
        when(imagenService.getImagenById(999)).thenReturn(null);
        ResponseEntity<Imagen> response = imagenController.getImagenById(999);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void createImagen_ShouldReturnCreated() {
        Imagen imagenToCreate = new Imagen(); imagenToCreate.setUrl("nueva.jpg");
        Imagen created = new Imagen(); created.setId(3); created.setUrl("nueva.jpg");
        when(imagenService.createImagen(imagenToCreate)).thenReturn(created);
        ResponseEntity<Imagen> response = imagenController.createImagen(imagenToCreate);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(3, response.getBody().getId());
    }

    @Test void updateImagen_WhenExists_ShouldReturnUpdated() {
        Imagen imagen = new Imagen(); imagen.setId(1); imagen.setUrl("actualizada.jpg");
        when(imagenService.updateImagen(1, imagen)).thenReturn(imagen);
        ResponseEntity<Imagen> response = imagenController.updateImagen(1, imagen);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("actualizada.jpg", response.getBody().getUrl());
    }

    @Test void updateImagen_WhenNotExists_ShouldReturnNotFound() {
        Imagen imagen = new Imagen(); imagen.setId(999); imagen.setUrl("inexistente.jpg");
        when(imagenService.updateImagen(999, imagen)).thenReturn(null);
        ResponseEntity<Imagen> response = imagenController.updateImagen(999, imagen);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void deleteImagen_ShouldCallService() {
        doNothing().when(imagenService).deleteImagen(1);
        ResponseEntity<Void> response = imagenController.deleteImagen(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(imagenService, times(1)).deleteImagen(1);
    }
}