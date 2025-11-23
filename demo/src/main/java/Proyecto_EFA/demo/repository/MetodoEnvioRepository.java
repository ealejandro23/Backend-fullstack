package Proyecto_EFA.demo.repository;

<<<<<<< HEAD
import Proyecto_EFA.demo.model.MetodoEnvio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoEnvioRepository extends JpaRepository<MetodoEnvio, Integer> {
    // Agrega este método faltante
    MetodoEnvio findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
=======
import org.springframework.data.jpa.repository.JpaRepository;
import Proyecto_EFA.demo.model.MetodoEnvio;

public interface MetodoEnvioRepository extends JpaRepository<MetodoEnvio, Integer> {
    
}
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
