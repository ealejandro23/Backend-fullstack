package Proyecto_EFA.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Proyecto_EFA.demo.model.MetodoPago;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {
    MetodoPago findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}