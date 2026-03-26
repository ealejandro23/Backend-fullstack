package Proyecto_EFA.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComunaModelTest {
    @Test
    void basicConstruction() {
        Comuna c = new Comuna();
        assertNotNull(c);
    }
}
