package Proyecto_EFA.demo.repository;

import Proyecto_EFA.demo.model.Imagen;
import Proyecto_EFA.demo.model.Producto;
import Proyecto_EFA.demo.repository.ImagenRepository;
import Proyecto_EFA.demo.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ImagenRepositoryTest {

    @Autowired
    private ImagenRepository imagenRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void saveImagen() {
        Producto p = new Producto();
        p.setNombre("P1");
        Producto savedP = productoRepository.save(p);

        Imagen i = new Imagen();
        i.setUrl("http://x");
        i.setProducto(savedP);
        Imagen saved = imagenRepository.save(i);
        assertNotNull(saved.getId());
    }
}
