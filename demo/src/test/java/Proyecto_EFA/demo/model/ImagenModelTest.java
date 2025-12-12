package Proyecto_EFA.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ImagenModelTest {
    @Test
    void basicConstruction() {
        Imagen i = new Imagen();
        assertNotNull(i);
    }
}
