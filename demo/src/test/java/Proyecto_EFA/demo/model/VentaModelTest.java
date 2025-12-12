package Proyecto_EFA.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VentaModelTest {
    @Test
    void basicConstruction() {
        Venta v = new Venta();
        assertNotNull(v);
    }
}
