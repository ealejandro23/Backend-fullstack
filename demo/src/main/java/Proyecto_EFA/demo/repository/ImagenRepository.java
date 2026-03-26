package Proyecto_EFA.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Proyecto_EFA.demo.model.Imagen;

public interface ImagenRepository extends JpaRepository<Imagen, Integer> {
    
}
