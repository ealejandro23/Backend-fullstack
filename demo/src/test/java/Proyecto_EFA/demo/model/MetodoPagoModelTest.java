package Proyecto_EFA.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MetodoPagoModelTest {
    @Test
    void basicConstruction() {
        MetodoPago m = new MetodoPago();
        assertNotNull(m);
    }
}
