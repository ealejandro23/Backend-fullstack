package Proyecto_EFA.demo.repository;

import Proyecto_EFA.demo.model.Materiales;
import Proyecto_EFA.demo.repository.MaterialesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MaterialesRepositoryTest {

    @Autowired
    private MaterialesRepository materialesRepository;

    @Test
    void saveMateriales() {
        Materiales m = new Materiales();
        m.setNombre("Mat1");
        Materiales saved = materialesRepository.save(m);
        assertNotNull(saved.getId());
    }
}
