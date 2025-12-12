package Proyecto_EFA.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MetodoEnvioModelTest {
    @Test
    void basicConstruction() {
        MetodoEnvio m = new MetodoEnvio();
        assertNotNull(m);
    }
}
