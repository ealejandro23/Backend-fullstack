package Proyecto_EFA.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import Proyecto_EFA.demo.model.Usuario;
import Proyecto_EFA.demo.service.UsuarioService;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {
    @Mock private UsuarioService usuarioService;
    @InjectMocks private UsuarioController usuarioController;

    @Test void createUsuario_ShouldReturnCreated() {
        Usuario usuarioToCreate = new Usuario();
        usuarioToCreate.setCorreo("test@test.com"); usuarioToCreate.setContrasena("123");
        Usuario created = new Usuario(); created.setId(1); created.setCorreo("test@test.com");
        when(usuarioService.create(usuarioToCreate)).thenReturn(created);
        ResponseEntity<?> response = usuarioController.createUsuario(usuarioToCreate);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, ((Usuario) response.getBody()).getId());
    }

    @Test void createUsuario_WhenDuplicateEmail_ShouldReturnBadRequest() {
        Usuario usuarioToCreate = new Usuario();
        usuarioToCreate.setCorreo("duplicate@test.com");
        when(usuarioService.create(usuarioToCreate)).thenThrow(new DataIntegrityViolationException("Duplicate"));
        ResponseEntity<?> response = usuarioController.createUsuario(usuarioToCreate);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test void login_WithValidCredentials_ShouldReturnToken() {
        Usuario usuario = new Usuario(); usuario.setId(1); usuario.setCorreo("test@test.com");
        when(usuarioService.login("test@test.com", "123")).thenReturn(usuario);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setCorreo("test@test.com"); loginRequest.setContrasena("123");
        ResponseEntity<?> response = usuarioController.login(loginRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test void login_WithInvalidCredentials_ShouldReturnUnauthorized() {
        when(usuarioService.login("wrong@test.com", "wrong")).thenReturn(null);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setCorreo("wrong@test.com"); loginRequest.setContrasena("wrong");
        ResponseEntity<?> response = usuarioController.login(loginRequest);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test void getAllUsuarios_ShouldReturnAll() {
        Usuario u1 = new Usuario(); u1.setId(1); u1.setNombre("Juan");
        Usuario u2 = new Usuario(); u2.setId(2); u2.setNombre("Pedro");
        List<Usuario> usuarios = Arrays.asList(u1, u2);
        when(usuarioService.getAllUsers()).thenReturn(usuarios);
        ResponseEntity<List<Usuario>> response = usuarioController.getAllUsuarios();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test void getUsuarioById_WhenExists_ShouldReturnUsuario() {
        Usuario usuario = new Usuario(); usuario.setId(1); usuario.setNombre("Juan");
        when(usuarioService.findById(1)).thenReturn(usuario);
        ResponseEntity<Usuario> response = usuarioController.getUsuarioById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Juan", response.getBody().getNombre());
    }

    @Test void searchUsuarios_ShouldReturnList() {
        Usuario usuario = new Usuario(); usuario.setId(1); usuario.setNombre("Juan");
        List<Usuario> usuarios = Arrays.asList(usuario);
        when(usuarioService.searchByNombre("Juan")).thenReturn(usuarios);
        ResponseEntity<List<Usuario>> response = usuarioController.searchUsuarios("Juan");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test void updateUsuario_WhenExists_ShouldReturnUpdated() {
        Usuario usuario = new Usuario(); usuario.setId(1); usuario.setNombre("Juan Actualizado");
        when(usuarioService.updateUsuario(usuario)).thenReturn(usuario);
        ResponseEntity<Usuario> response = usuarioController.updateUsuario(1, usuario);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Juan Actualizado", response.getBody().getNombre());
    }

    @Test void partialUpdateUsuario_WhenExists_ShouldReturnUpdated() {
        Usuario usuario = new Usuario(); usuario.setId(1); usuario.setNombre("Juan Parcial");
        when(usuarioService.partialUpdateUsuario(1, usuario)).thenReturn(usuario);
        ResponseEntity<Usuario> response = usuarioController.partialUpdateUsuario(1, usuario);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Juan Parcial", response.getBody().getNombre());
    }

    @Test void deleteUsuario_ShouldCallService() {
        doNothing().when(usuarioService).deleteUsuario(1);
        ResponseEntity<Void> response = usuarioController.deleteUsuario(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(usuarioService, times(1)).deleteUsuario(1);
    }

    @Test void getUsuariosByRol_ShouldReturnList() {
        Usuario usuario = new Usuario(); usuario.setId(1); usuario.setNombre("Admin");
        List<Usuario> usuarios = Arrays.asList(usuario);
        when(usuarioService.getUsuariosByRol(1)).thenReturn(usuarios);
        ResponseEntity<List<Usuario>> response = usuarioController.getUsuariosByRol(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }
}