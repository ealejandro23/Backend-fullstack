package Proyecto_EFA.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ColorModelTest {
    @Test
    void basicConstruction() {
        Color c = new Color();
        assertNotNull(c);
    }
}
