package Proyecto_EFA.demo.repository;

import Proyecto_EFA.demo.model.Usuario;
import Proyecto_EFA.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void saveUsuario() {
        Usuario u = new Usuario();
        u.setNombre("u1");
        u.setCorreo("a@b.com");
        u.setContrasena("x");
        Usuario saved = usuarioRepository.save(u);
        assertNotNull(saved.getId());
    }
}
