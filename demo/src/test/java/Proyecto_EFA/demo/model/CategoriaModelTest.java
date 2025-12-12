package Proyecto_EFA.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoriaModelTest {
    @Test
    void basicConstruction() {
        Categoria c = new Categoria();
        assertNotNull(c);
    }
}
