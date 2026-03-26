package Proyecto_EFA.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Proyecto_EFA.demo.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {
    
	@Query("SELECT e FROM Estado e WHERE e.nombre = :nombre")
	Optional<Estado> findByNombre(@Param("nombre") String nombre);
	
    @Query("SELECT e FROM Estado e ORDER BY e.nombre ASC")
	java.util.List<Estado> findAllOrderedByName();

} 
