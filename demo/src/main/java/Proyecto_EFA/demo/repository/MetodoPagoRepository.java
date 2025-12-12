package Proyecto_EFA.demo.repository;

import Proyecto_EFA.demo.model.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {
    // Agrega este método faltante
    MetodoPago findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}