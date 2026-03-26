package Proyecto_EFA.demo.repository;

import Proyecto_EFA.demo.model.ProductoVenta;
import Proyecto_EFA.demo.repository.ProductoVentaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductoVentaRepositoryTest {

    @Autowired
    private ProductoVentaRepository productoVentaRepository;

    @Test
    void saveProductoVenta() {
        ProductoVenta pv = new ProductoVenta();
        pv.setCantidad(1);
        pv.setPrecioUnitario(10.0);
        ProductoVenta saved = productoVentaRepository.save(pv);
        assertNotNull(saved.getId());
    }
}
