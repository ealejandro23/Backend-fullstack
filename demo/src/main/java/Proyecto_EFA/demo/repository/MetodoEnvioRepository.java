package Proyecto_EFA.demo.repository;

import Proyecto_EFA.demo.model.MetodoEnvio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoEnvioRepository extends JpaRepository<MetodoEnvio, Integer> {
    // Agrega este método faltante
    MetodoEnvio findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}