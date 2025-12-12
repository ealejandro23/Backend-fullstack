package Proyecto_EFA.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EstadoModelTest {
    @Test
    void basicConstruction() {
        Estado e = new Estado();
        assertNotNull(e);
    }
}
