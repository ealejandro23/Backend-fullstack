package Proyecto_EFA.demo.repository;

import Proyecto_EFA.demo.model.Categorias;
import Proyecto_EFA.demo.repository.CategoriasRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoriasRepositoryTest {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Test
    void saveAndFindByIdAndFindAll() {
        Categorias c = new Categorias();
        c.setNombre("RepoTest");
        c.setDescripcion("Desc");

        Categorias saved = categoriasRepository.save(c);
        assertNotNull(saved.getId(), "Id should be generated");

        Optional<Categorias> found = categoriasRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("RepoTest", found.get().getNombre());

        List<Categorias> all = categoriasRepository.findAll();
        assertTrue(all.size() >= 1);
    }
}
