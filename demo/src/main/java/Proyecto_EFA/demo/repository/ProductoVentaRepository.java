package Proyecto_EFA.demo.repository;

import Proyecto_EFA.demo.model.ProductoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoVentaRepository extends JpaRepository<ProductoVenta, Integer> {
    
    @Query("SELECT pv FROM ProductoVenta pv WHERE pv.venta.id = :ventaId")
    List<ProductoVenta> findByVentaId(@Param("ventaId") Integer ventaId);
    
    @Query("SELECT pv FROM ProductoVenta pv WHERE pv.producto.id = :productoId")
    List<ProductoVenta> findByProductoId(@Param("productoId") Integer productoId);
    
    @Query("SELECT pv FROM ProductoVenta pv WHERE pv.venta.id = :ventaId AND pv.producto.id = :productoId")
    ProductoVenta findByVentaIdAndProductoId(@Param("ventaId") Long ventaId, @Param("productoId") Integer productoId);

    @Query("SELECT SUM(pv.cantidad) FROM ProductoVenta pv WHERE pv.producto.id = :productoId")
    Integer getTotalQuantitySoldByProducto(@Param("productoId") Integer productoId);

    @Query("SELECT SUM(pv.cantidad * pv.precioUnitario) FROM ProductoVenta pv WHERE pv.producto.id = :productoId")
    Double getTotalRevenueByProducto(@Param("productoId") Integer productoId);

    // CONSULTAS CORREGIDAS - Sin parámetros en LIMIT
    @Query(value = "SELECT pv.producto_id, SUM(pv.cantidad) as total_cantidad FROM producto_venta pv GROUP BY pv.producto_id ORDER BY total_cantidad DESC LIMIT 10", nativeQuery = true)
    List<Object[]> findTop10SellingProducts();

    @Query(value = "SELECT pv.producto_id, SUM(pv.cantidad) as total_cantidad FROM producto_venta pv GROUP BY pv.producto_id ORDER BY total_cantidad DESC LIMIT 5", nativeQuery = true)
    List<Object[]> findTop5SellingProducts();

    // Consulta para obtener todos (para el método con límite variable)
    @Query(value = "SELECT pv.producto_id, SUM(pv.cantidad) as total_cantidad FROM producto_venta pv GROUP BY pv.producto_id ORDER BY total_cantidad DESC", nativeQuery = true)
    List<Object[]> findAllSellingProducts();

    @Query("SELECT COUNT(pv) FROM ProductoVenta pv WHERE pv.venta.id = :ventaId")
    Integer countItemsByVenta(@Param("ventaId") Integer ventaId);
}
