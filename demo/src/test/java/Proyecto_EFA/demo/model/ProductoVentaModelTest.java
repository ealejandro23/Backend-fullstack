package Proyecto_EFA.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductoVentaModelTest {
    @Test
    void basicConstruction() {
        ProductoVenta pv = new ProductoVenta();
        assertNotNull(pv);
    }
}
