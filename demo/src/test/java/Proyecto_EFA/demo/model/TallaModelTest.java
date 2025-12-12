package Proyecto_EFA.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TallaModelTest {
    @Test
    void basicConstruction() {
        Talla t = new Talla();
        assertNotNull(t);
    }
}
