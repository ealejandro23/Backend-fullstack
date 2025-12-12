package Proyecto_EFA.demo.repository;

import Proyecto_EFA.demo.model.Rol;
import Proyecto_EFA.demo.repository.RolRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RolRepositoryTest {

    @Autowired
    private RolRepository rolRepository;

    @Test
    void saveRol() {
        Rol r = new Rol();
        r.setNombre("ADMIN");
        Rol saved = rolRepository.save(r);
        assertNotNull(saved.getId());
    }
}
