package Proyecto_EFA.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import Proyecto_EFA.demo.model.Categorias;
import Proyecto_EFA.demo.service.CategoriasService;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaControllerTest {
    @Mock private CategoriasService categoriasService;
    @InjectMocks private CategoriaController categoriaController;

    @Test void getAllCategorias_ShouldReturnAll() {
        Categorias c1 = new Categorias(); c1.setId(1); c1.setNombre("Ropa");
        Categorias c2 = new Categorias(); c2.setId(2); c2.setNombre("Calzado");
        List<Categorias> categorias = Arrays.asList(c1, c2);
        when(categoriasService.findAll()).thenReturn(categorias);
        ResponseEntity<List<Categorias>> response = categoriaController.getAllCategorias();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test void getAllCategorias_WhenEmpty_ShouldReturnNoContent() {
        when(categoriasService.findAll()).thenReturn(Arrays.asList());
        ResponseEntity<List<Categorias>> response = categoriaController.getAllCategorias();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test void getCategoriaById_WhenExists_ShouldReturnCategoria() {
        Categorias categoria = new Categorias(); categoria.setId(1); categoria.setNombre("Ropa");
        when(categoriasService.findById(1)).thenReturn(categoria);
        ResponseEntity<Categorias> response = categoriaController.getCategoriaById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Ropa", response.getBody().getNombre());
    }

    @Test void getCategoriaById_WhenNotExists_ShouldReturnNotFound() {
        when(categoriasService.findById(999)).thenReturn(null);
        ResponseEntity<Categorias> response = categoriaController.getCategoriaById(999);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void createCategoria_ShouldReturnCreated() {
        Categorias categoriaToCreate = new Categorias(); categoriaToCreate.setNombre("Nueva Categoría");
        Categorias created = new Categorias(); created.setId(3); created.setNombre("Nueva Categoría");
        when(categoriasService.save(categoriaToCreate)).thenReturn(created);
        ResponseEntity<Categorias> response = categoriaController.createCategoria(categoriaToCreate);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(3, response.getBody().getId());
    }

    @Test void updateCategoria_WhenExists_ShouldReturnUpdated() {
        Categorias categoria = new Categorias(); categoria.setId(1); categoria.setNombre("Ropa Actualizada");
        when(categoriasService.partialUpdate(categoria)).thenReturn(categoria);
        ResponseEntity<Categorias> response = categoriaController.updateCategoria(1, categoria);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Ropa Actualizada", response.getBody().getNombre());
    }

    @Test void deleteCategoria_ShouldCallService() {
        doNothing().when(categoriasService).deleteById(1);
        ResponseEntity<Void> response = categoriaController.deleteCategoria(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(categoriasService, times(1)).deleteById(1);
    }
}
