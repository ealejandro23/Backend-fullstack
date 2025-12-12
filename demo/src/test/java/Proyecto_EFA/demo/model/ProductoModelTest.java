package Proyecto_EFA.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductoModelTest {
    @Test
    void basicConstruction() {
        Producto p = new Producto();
        assertNotNull(p);
    }
}
