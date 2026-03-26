package Proyecto_EFA.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MaterialesModelTest {
    @Test
    void basicConstruction() {
        Materiales m = new Materiales();
        assertNotNull(m);
    }
}
