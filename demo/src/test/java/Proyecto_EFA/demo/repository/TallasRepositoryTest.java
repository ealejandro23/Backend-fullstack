package Proyecto_EFA.demo.repository;

import Proyecto_EFA.demo.model.Tallas;
import Proyecto_EFA.demo.repository.TallasRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TallasRepositoryTest {

    @Autowired
    private TallasRepository tallasRepository;

    @Test
    void saveTallas() {
        Tallas t = new Tallas();
        t.setNombre("T1");
        Tallas saved = tallasRepository.save(t);
        assertNotNull(saved.getId());
    }
}
