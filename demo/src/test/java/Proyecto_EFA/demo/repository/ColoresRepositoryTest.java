package Proyecto_EFA.demo.repository;

import Proyecto_EFA.demo.model.Colores;
import Proyecto_EFA.demo.repository.ColoresRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ColoresRepositoryTest {

    @Autowired
    private ColoresRepository coloresRepository;

    @Test
    void saveColores() {
        Colores c = new Colores();
        c.setNombre("C1");
        Colores saved = coloresRepository.save(c);
        assertNotNull(saved.getId());
    }
}
