package Proyecto_EFA.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Proyecto_EFA.demo.model.Tallas;

public interface TallasRepository extends JpaRepository<Tallas, Integer> {

    Optional<Tallas> findByNombre(String nombre);
    
}
