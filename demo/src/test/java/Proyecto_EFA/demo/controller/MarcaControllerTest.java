package Proyecto_EFA.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import Proyecto_EFA.demo.model.Marca;
import Proyecto_EFA.demo.service.MarcaService;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MarcaControllerTest {
    @Mock private MarcaService marcaService;
    @InjectMocks private MarcaController marcaController;

    @Test void getAllMarcas_ShouldReturnAll() {
        Marca m1 = new Marca(); m1.setId(1); m1.setNombre("Nike");
        Marca m2 = new Marca(); m2.setId(2); m2.setNombre("Adidas");
        List<Marca> marcas = Arrays.asList(m1, m2);
        when(marcaService.getAllMarcas()).thenReturn(marcas);
        ResponseEntity<List<Marca>> response = marcaController.getAllMarcas();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test void getMarcaById_WhenExists_ShouldReturnMarca() {
        Marca marca = new Marca(); marca.setId(1); marca.setNombre("Nike");
        when(marcaService.getMarcaById(1)).thenReturn(marca);
        ResponseEntity<Marca> response = marcaController.getMarcaById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Nike", response.getBody().getNombre());
    }

    @Test void getMarcaById_WhenNotExists_ShouldReturnNotFound() {
        when(marcaService.getMarcaById(999)).thenReturn(null);
        ResponseEntity<Marca> response = marcaController.getMarcaById(999);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void createMarca_ShouldReturnCreated() {
        Marca marcaToCreate = new Marca(); marcaToCreate.setNombre("Nueva Marca");
        Marca created = new Marca(); created.setId(3); created.setNombre("Nueva Marca");
        when(marcaService.createMarca(marcaToCreate)).thenReturn(created);
        ResponseEntity<Marca> response = marcaController.createMarca(marcaToCreate);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(3, response.getBody().getId());
    }

    @Test void updateMarca_WhenExists_ShouldReturnUpdated() {
        Marca marca = new Marca(); marca.setId(1); marca.setNombre("Nike Actualizado");
        when(marcaService.updateMarca(1, marca)).thenReturn(marca);
        ResponseEntity<Marca> response = marcaController.updateMarca(1, marca);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Nike Actualizado", response.getBody().getNombre());
    }

    @Test void updateMarca_WhenNotExists_ShouldReturnNotFound() {
        Marca marca = new Marca(); marca.setId(999); marca.setNombre("Inexistente");
        when(marcaService.updateMarca(999, marca)).thenReturn(null);
        ResponseEntity<Marca> response = marcaController.updateMarca(999, marca);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void deleteMarca_ShouldCallService() {
        doNothing().when(marcaService).deleteMarca(1);
        ResponseEntity<Void> response = marcaController.deleteMarca(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(marcaService, times(1)).deleteMarca(1);
    }
}