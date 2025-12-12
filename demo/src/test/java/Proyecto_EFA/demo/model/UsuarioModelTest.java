package Proyecto_EFA.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioModelTest {
    @Test
    void basicConstruction() {
        Usuario u = new Usuario();
        assertNotNull(u);
    }
}
