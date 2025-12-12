package Proyecto_EFA.demo.repository;

import Proyecto_EFA.demo.model.Usuario;
import Proyecto_EFA.demo.model.Venta;
import Proyecto_EFA.demo.repository.UsuarioRepository;
import Proyecto_EFA.demo.repository.VentaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class VentaRepositoryTest {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void saveVenta() {
        Usuario u = new Usuario();
        u.setNombre("U1");
        u.setCorreo("u1@x.com");
        u.setContrasena("pwd");
        Usuario savedU = usuarioRepository.save(u);

        Venta v = new Venta();
        v.setNumeroVenta("NV-1");
        v.setUsuario(savedU);
        Venta saved = ventaRepository.save(v);
        assertNotNull(saved.getId());
    }
}
