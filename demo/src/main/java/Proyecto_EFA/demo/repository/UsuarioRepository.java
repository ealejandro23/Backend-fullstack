package Proyecto_EFA.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Proyecto_EFA.demo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
	Usuario findByNombre(String nombre);
    
	Usuario findByCorreo(String correo);
    
	java.util.List<Usuario> findByNombreContainingIgnoreCase(String nombre);
	
	@Query("SELECT u FROM Usuario u WHERE u.rol.id = :rolId")
	List<Usuario> findByRolId(@Param("rolId") Integer rolId);
	
	@Query("SELECT u FROM Usuario u WHERE u.direccion.comuna.id = :comunaId")
	List<Usuario> findByComunaId(@Param("comunaId") Integer comunaId);
	
	@Query("SELECT u FROM Usuario u WHERE u.direccion.comuna.region.id = :regionId")
	List<Usuario> findByRegionId(@Param("regionId") Integer regionId);

	@Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
	Optional<Usuario> findByNombreExact(@Param("nombre") String nombre);
	
	@Query("SELECT u FROM Usuario u WHERE u.rol.nombre = 'ADMIN'")
	List<Usuario> findAllAdmins();

	@Query("SELECT u FROM Usuario u WHERE u.rol.nombre != 'ADMIN'")
	List<Usuario> findAllCustomers();

	@Query("SELECT COUNT(u) FROM Usuario u WHERE u.rol.id = :rolId")
	int countByRol(@Param("rolId") Integer rolId);

}
