package Proyecto_EFA.demo.repository;

import Proyecto_EFA.demo.model.Marca;
import Proyecto_EFA.demo.repository.MarcaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MarcaRepositoryTest {

    @Autowired
    private MarcaRepository marcaRepository;

    @Test
    void saveMarca() {
        Marca m = new Marca();
        m.setNombre("M1");
        Marca saved = marcaRepository.save(m);
        assertNotNull(saved.getId());
    }
}
