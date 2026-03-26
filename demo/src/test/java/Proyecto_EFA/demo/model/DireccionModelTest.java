package Proyecto_EFA.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DireccionModelTest {
    @Test
    void basicConstruction() {
        Direccion d = new Direccion();
        assertNotNull(d);
    }
}
