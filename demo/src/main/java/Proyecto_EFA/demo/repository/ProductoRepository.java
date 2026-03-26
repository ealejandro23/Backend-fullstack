package Proyecto_EFA.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Proyecto_EFA.demo.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("SELECT p FROM Producto p WHERE p.marca.id = :marcaId")
    List<Producto> findByMarcaId(@Param("marcaId") Integer marcaId);
    
    // ✅ MÉTODO CRÍTICO para la solución (filtra por ID, obtenido del nombre)
    @Query("SELECT p FROM Producto p WHERE p.categorias.id = :categoriaId") 
    List<Producto> findByCategoriaId(@Param("categoriaId") Integer categoriaId);
    
    @Query("SELECT p FROM Producto p WHERE p.precio BETWEEN :precioMin AND :precioMax ORDER BY p.precio ASC")
    List<Producto> findByPriceRange(@Param("precioMin") Double precioMin, @Param("precioMax") Double precioMax);
    
    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Producto> searchByNombre(@Param("nombre") String nombre);
    
    @Query("SELECT p FROM Producto p WHERE p.colores.id = :colorId")
    List<Producto> findByColorId(@Param("colorId") Integer colorId);
    
    @Query("SELECT p FROM Producto p WHERE p.materiales.id = :materialId")
    List<Producto> findByMaterialId(@Param("materialId") Integer materialId);
    
    @Query("SELECT p FROM Producto p WHERE p.tallas.id = :tallaId") 
    List<Producto> findByTallaId(@Param("tallaId") Integer tallaId);
    
    @Query("SELECT p FROM Producto p WHERE p.marca.id = :marcaId AND p.categorias.id = :categoriaId")
    List<Producto> findByMarcaIdAndCategoriaId(@Param("marcaId") Integer marcaId, @Param("categoriaId") Integer categoriaId);
    
    List<Producto> findTop10ByOrderByPrecioDesc();
    List<Producto> findTop10ByOrderByPrecioAsc();
    
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByPrecioGreaterThanEqual(Double precioMin);
    List<Producto> findByPrecioLessThanEqual(Double precioMax);
    List<Producto> findByStockGreaterThan(Integer stock);
    Optional<Producto> findByCodigo(String codigo);
}
