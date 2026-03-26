package Proyecto_EFA.demo.repository;

import Proyecto_EFA.demo.model.Producto;
import Proyecto_EFA.demo.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void saveProducto() {
        Producto p = new Producto();
        p.setNombre("X");
        Producto saved = productoRepository.save(p);
        assertNotNull(saved.getId());
    }
}
