package Proyecto_EFA.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import Proyecto_EFA.demo.model.Rol;
import Proyecto_EFA.demo.service.RolService;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RolControllerTest {
    @Mock private RolService rolService;
    @InjectMocks private RolController rolController;

    @Test void getAllRoles_ShouldReturnAllRoles() {
        Rol rol1 = new Rol(1, "Admin");
        Rol rol2 = new Rol(2, "Cliente");
        List<Rol> roles = Arrays.asList(rol1, rol2);
        when(rolService.getAllRoles()).thenReturn(roles);
        ResponseEntity<List<Rol>> response = rolController.getAllRoles();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(rolService, times(1)).getAllRoles();
    }

    @Test void getRolById_WhenRolExists_ShouldReturnRol() {
        Rol rol = new Rol(1, "Admin");
        when(rolService.getRolById(1)).thenReturn(rol);
        ResponseEntity<Rol> response = rolController.getRolById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Admin", response.getBody().getNombre());
    }

    @Test void getRolById_WhenRolNotExists_ShouldReturnNotFound() {
        when(rolService.getRolById(999)).thenReturn(null);
        ResponseEntity<Rol> response = rolController.getRolById(999);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void createRol_ShouldReturnCreatedRol() {
        Rol rolToCreate = new Rol(null, "NuevoRol");
        Rol createdRol = new Rol(3, "NuevoRol");
        when(rolService.createRol(rolToCreate)).thenReturn(createdRol);
        ResponseEntity<Rol> response = rolController.createRol(rolToCreate);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(3, response.getBody().getId());
    }

    @Test void updateRol_WhenRolExists_ShouldReturnUpdated() {
        Rol existingRol = new Rol(1, "Admin");
        Rol updatedRol = new Rol(1, "Administrador");
        when(rolService.updateRol(1, updatedRol)).thenReturn(updatedRol);
        ResponseEntity<Rol> response = rolController.updateRol(1, updatedRol);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Administrador", response.getBody().getNombre());
    }

    @Test void updateRol_WhenRolNotExists_ShouldReturnNotFound() {
        Rol rolDetails = new Rol(999, "Inexistente");
        when(rolService.updateRol(999, rolDetails)).thenReturn(null);
        ResponseEntity<Rol> response = rolController.updateRol(999, rolDetails);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void deleteRol_ShouldCallService() {
        doNothing().when(rolService).deleteRol(1);
        ResponseEntity<Void> response = rolController.deleteRol(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(rolService, times(1)).deleteRol(1);
    }
}