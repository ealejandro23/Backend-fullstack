package Proyecto_EFA.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Proyecto_EFA.demo.model.Materiales;

public interface MaterialesRepository extends JpaRepository<Materiales, Integer> {
    
    Optional<Materiales> findByNombre(String nombre);
    List<Materiales> findAllOrderedByName();
}
