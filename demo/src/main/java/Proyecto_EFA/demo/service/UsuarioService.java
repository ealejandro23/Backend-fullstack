package Proyecto_EFA.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import Proyecto_EFA.demo.model.Usuario;
import Proyecto_EFA.demo.repository.UsuarioRepository;

@Service
@Transactional
@SuppressWarnings("null")
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // CRUD básico
    public List<Usuario> getAllUsers() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuario : usuarios) {
            usuario.setContrasena(null);
        }
        return usuarios;
    }

    public Usuario findById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setContrasena(null);
        }
        return usuario;
    }      

    public Usuario login(String nombreUsuario, String contrasena) {
        Usuario usuario = usuarioRepository.findByNombre(nombreUsuario);
        if (usuario == null) {
            usuario = usuarioRepository.findByCorreo(nombreUsuario);
        }
        if (usuario != null && passwordEncoder.matches(contrasena, usuario.getContrasena())) {
            usuario.setContrasena(null);
            return usuario;
        }
        return null;
    }

    public List<Usuario> searchByNombre(String nombre) {
        List<Usuario> usuarios = usuarioRepository.findByNombreContainingIgnoreCase(nombre);
        for (Usuario u : usuarios) {
            u.setContrasena(null);
        }
        return usuarios;
    }

    public Usuario updateUsuario(Usuario usuario) {
        return save(usuario);
    }

    public Usuario save(Usuario usuario) {
        if (usuario.getContrasena() != null) {
            String encodedPassword = passwordEncoder.encode(usuario.getContrasena());
            usuario.setContrasena(encodedPassword);
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario partialUpdateUsuario(Integer id, Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            if (usuarioDetails.getNombre() != null) {
                usuario.setNombre(usuarioDetails.getNombre());
            }
            if (usuarioDetails.getCorreo() != null) {
                usuario.setCorreo(usuarioDetails.getCorreo());
            }
            if (usuarioDetails.getContrasena() != null) {
                String encodedPassword = passwordEncoder.encode(usuarioDetails.getContrasena());
                usuario.setContrasena(encodedPassword);
            }
            if (usuarioDetails.getRol() != null) {
                usuario.setRol(usuarioDetails.getRol());
            }
            if (usuarioDetails.getDireccion() != null) {
                usuario.setDireccion(usuarioDetails.getDireccion());
            }
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
    
    // Métodos de búsqueda avanzados
    public List<Usuario> getUsuariosByRol(Integer rolId) {
        List<Usuario> usuarios = usuarioRepository.findByRolId(rolId);
        for (Usuario u : usuarios) {
            u.setContrasena(null);
        }
        return usuarios;
    }
    
    public List<Usuario> getUsuariosByComuna(Integer comunaId) {
        List<Usuario> usuarios = usuarioRepository.findByComunaId(comunaId);
        for (Usuario u : usuarios) {
            u.setContrasena(null);
        }
        return usuarios;
    }
    
    public List<Usuario> getUsuariosByRegion(Integer regionId) {
        List<Usuario> usuarios = usuarioRepository.findByRegionId(regionId);
        for (Usuario u : usuarios) {
            u.setContrasena(null);
        }
        return usuarios;
    }
    
    public List<Usuario> getAllAdmins() {
        List<Usuario> admins = usuarioRepository.findAllAdmins();
        for (Usuario u : admins) {
            u.setContrasena(null);
        }
        return admins;
    }
    
    public List<Usuario> getAllCustomers() {
        List<Usuario> customers = usuarioRepository.findAllCustomers();
        for (Usuario u : customers) {
            u.setContrasena(null);
        }
        return customers;
    }
    
    public int countByRol(Integer rolId) {
        return 
        usuarioRepository.countByRol(rolId);
    }

    public Usuario getUsuarioById(Integer id) {
    return usuarioRepository.findById(id).orElse(null);
}

}
