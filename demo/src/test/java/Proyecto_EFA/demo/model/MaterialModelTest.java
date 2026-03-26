package Proyecto_EFA.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MaterialModelTest {
    @Test
    void basicConstruction() {
        Material m = new Material();
        assertNotNull(m);
    }
}
