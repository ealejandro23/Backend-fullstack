package Proyecto_EFA.demo.repository;

import Proyecto_EFA.demo.model.Estado;
import Proyecto_EFA.demo.repository.EstadoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EstadoRepositoryTest {

    @Autowired
    private EstadoRepository estadoRepository;

    @Test
    void saveEstado() {
        Estado e = new Estado();
        e.setNombre("Pendiente");
        Estado saved = estadoRepository.save(e);
        assertNotNull(saved.getId());
    }
}
