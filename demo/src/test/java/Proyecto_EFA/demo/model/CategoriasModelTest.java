package Proyecto_EFA.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoriasModelTest {

    @Test
    void lombokGettersSettersEqualsHashCodeToString() {
        Categorias c = new Categorias();
        c.setId(42);
        c.setNombre("TestNombre");
        c.setDescripcion("Desc");

        assertEquals(42, c.getId());
        assertEquals("TestNombre", c.getNombre());
        assertEquals("Desc", c.getDescripcion());

        Categorias c2 = new Categorias();
        c2.setId(42);
        c2.setNombre("TestNombre");
        c2.setDescripcion("Desc");

        assertEquals(c, c2, "Lombok equals should consider same fields");
        assertEquals(c.hashCode(), c2.hashCode());
        assertTrue(c.toString().contains("TestNombre"));
    }
}
