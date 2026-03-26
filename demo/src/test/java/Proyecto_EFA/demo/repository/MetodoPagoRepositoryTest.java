package Proyecto_EFA.demo.repository;

import Proyecto_EFA.demo.model.MetodoPago;
import Proyecto_EFA.demo.repository.MetodoPagoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MetodoPagoRepositoryTest {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Test
    void saveMetodoPago() {
        MetodoPago m = new MetodoPago();
        m.setNombre("Pago1");
        MetodoPago saved = metodoPagoRepository.save(m);
        assertNotNull(saved.getId());
    }
}
