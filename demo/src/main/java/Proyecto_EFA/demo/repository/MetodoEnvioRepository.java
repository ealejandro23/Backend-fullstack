package Proyecto_EFA.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Proyecto_EFA.demo.model.MetodoEnvio;

@Repository
public interface MetodoEnvioRepository extends JpaRepository<MetodoEnvio, Integer> {
    MetodoEnvio findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}