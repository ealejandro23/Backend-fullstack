package Proyecto_EFA.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Proyecto_EFA.demo.model.MetodoEnvio;

public interface MetodoEnvioRepository extends JpaRepository<MetodoEnvio, Integer> {
    
}
