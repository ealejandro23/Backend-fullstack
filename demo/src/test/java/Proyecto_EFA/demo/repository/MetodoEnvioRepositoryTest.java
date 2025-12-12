package Proyecto_EFA.demo.repository;

import Proyecto_EFA.demo.model.MetodoEnvio;
import Proyecto_EFA.demo.repository.MetodoEnvioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MetodoEnvioRepositoryTest {

    @Autowired
    private MetodoEnvioRepository metodoEnvioRepository;

    @Test
    void saveMetodoEnvio() {
        MetodoEnvio m = new MetodoEnvio();
        m.setNombre("Envio1");
        m.setPrecio(10.0);
        MetodoEnvio saved = metodoEnvioRepository.save(m);
        assertNotNull(saved.getId());
    }
}
