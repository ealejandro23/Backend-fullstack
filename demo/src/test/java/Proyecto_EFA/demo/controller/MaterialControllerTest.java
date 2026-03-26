package Proyecto_EFA.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import Proyecto_EFA.demo.model.Materiales;
import Proyecto_EFA.demo.service.MaterialesService;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MaterialControllerTest {
    @Mock private MaterialesService materialesService;
    @InjectMocks private MaterialController materialController;

    @Test void getAllMateriales_ShouldReturnAll() {
        Materiales mat1 = new Materiales();
        mat1.setId(1); mat1.setNombre("Algodón");
        Materiales mat2 = new Materiales();
        mat2.setId(2); mat2.setNombre("Poliester");
        List<Materiales> materiales = Arrays.asList(mat1, mat2);
        when(materialesService.getAllMateriales()).thenReturn(materiales);
        ResponseEntity<List<Materiales>> response = materialController.getAllMateriales();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test void getMaterialById_WhenExists_ShouldReturnMaterial() {
        Materiales material = new Materiales();
        material.setId(1); material.setNombre("Algodón");
        when(materialesService.getMaterialById(1)).thenReturn(material);
        ResponseEntity<Materiales> response = materialController.getMaterialById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Algodón", response.getBody().getNombre());
    }

    @Test void getMaterialById_WhenNotExists_ShouldReturnNotFound() {
        when(materialesService.getMaterialById(999)).thenReturn(null);
        ResponseEntity<Materiales> response = materialController.getMaterialById(999);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void createMaterial_ShouldReturnCreated() {
        Materiales materialToCreate = new Materiales();
        materialToCreate.setNombre("Nuevo Material");
        Materiales created = new Materiales();
        created.setId(3); created.setNombre("Nuevo Material");
        when(materialesService.createMaterial(materialToCreate)).thenReturn(created);
        ResponseEntity<Materiales> response = materialController.createMaterial(materialToCreate);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(3, response.getBody().getId());
    }

    @Test void updateMaterial_WhenExists_ShouldReturnUpdated() {
        Materiales existing = new Materiales();
        existing.setId(1); existing.setNombre("Algodón");
        Materiales updated = new Materiales();
        updated.setId(1); updated.setNombre("Algodón Orgánico");
        when(materialesService.updateMaterial(1, updated)).thenReturn(updated);
        ResponseEntity<Materiales> response = materialController.updateMaterial(1, updated);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Algodón Orgánico", response.getBody().getNombre());
    }

    @Test void updateMaterial_WhenNotExists_ShouldReturnNotFound() {
        Materiales material = new Materiales();
        material.setId(999); material.setNombre("Inexistente");
        when(materialesService.updateMaterial(999, material)).thenReturn(null);
        ResponseEntity<Materiales> response = materialController.updateMaterial(999, material);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void deleteMaterial_ShouldCallService() {
        doNothing().when(materialesService).deleteMaterial(1);
        ResponseEntity<Void> response = materialController.deleteMaterial(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(materialesService, times(1)).deleteMaterial(1);
    }
}