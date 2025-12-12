package Proyecto_EFA.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MarcaModelTest {
    @Test
    void basicConstruction() {
        Marca m = new Marca();
        assertNotNull(m);
    }
}
