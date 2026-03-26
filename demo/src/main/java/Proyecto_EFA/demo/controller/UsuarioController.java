package Proyecto_EFA.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Proyecto_EFA.demo.model.Usuario;
import Proyecto_EFA.demo.service.UsuarioService;
import lombok.Data;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {
        try {
            usuario.setId(null);
            Usuario usuarioNew = usuarioService.create(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioNew);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg", "El correo ya está registrado o faltan datos obligatorios."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("msg", "Error al crear el usuario."));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Usuario usuarioAutenticado = usuarioService.login(loginRequest.getCorreo(), loginRequest.getContrasena());

        if (usuarioAutenticado != null) {
            TokenResponse response = new TokenResponse(
                "MOCK_TOKEN_JWT",
                usuarioAutenticado
            );
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body(Map.of("msg", "Credenciales inválidas."));
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsers();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Usuario>> searchUsuarios(@RequestParam String nombre) {
        List<Usuario> usuarios = usuarioService.searchByNombre(nombre);
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        Usuario updatedUsuario = usuarioService.updateUsuario(usuario);
        if (updatedUsuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUsuario);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> partialUpdateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Usuario updatedUsuario = usuarioService.partialUpdateUsuario(id, usuario);
        if (updatedUsuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/rol/{rolId}")
    public ResponseEntity<List<Usuario>> getUsuariosByRol(@PathVariable Integer rolId) {
        List<Usuario> usuarios = usuarioService.getUsuariosByRol(rolId);
        return ResponseEntity.ok(usuarios);
    }
    
    @GetMapping("/comuna/{comunaId}")
    public ResponseEntity<List<Usuario>> getUsuariosByComuna(@PathVariable Integer comunaId) {
        List<Usuario> usuarios = usuarioService.getUsuariosByComuna(comunaId);
        return ResponseEntity.ok(usuarios);
    }
    
    @GetMapping("/region/{regionId}")
    public ResponseEntity<List<Usuario>> getUsuariosByRegion(@PathVariable Integer regionId) {
        List<Usuario> usuarios = usuarioService.getUsuariosByRegion(regionId);
        return ResponseEntity.ok(usuarios);
    }
    
    @GetMapping("/administradores")
    public ResponseEntity<List<Usuario>> getAllAdmins() {
        List<Usuario> admins = usuarioService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }
    
    @GetMapping("/clientes")
    public ResponseEntity<List<Usuario>> getAllCustomers() {
        List<Usuario> customers = usuarioService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
    
    @GetMapping("/contar/rol/{rolId}")
    public ResponseEntity<Integer> countByRol(@PathVariable Integer rolId) {
        int count = usuarioService.countByRol(rolId);
        return ResponseEntity.ok(count);
    }
}

@Data
class LoginRequest {
    private String correo;
    private String contrasena;
}

@Data
class TokenResponse {
    private String token;
    private Usuario usuario;
    
    public TokenResponse(String token, Usuario usuario) {
        this.token = token;
        usuario.setContrasena(null); 
        this.usuario = usuario;
    }
}
