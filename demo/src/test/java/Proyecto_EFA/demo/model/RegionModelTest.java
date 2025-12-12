package Proyecto_EFA.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RegionModelTest {
    @Test
    void basicConstruction() {
        Region r = new Region();
        assertNotNull(r);
    }
}
