package Proyecto_EFA.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Proyecto_EFA.demo.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {
	
	@Query("SELECT v FROM Venta v WHERE v.usuario.id = :usuarioId")
	List<Venta> findByUsuarioId(@Param("usuarioId") Integer usuarioId);
	
	@Query("SELECT v FROM Venta v WHERE v.estado.id = :estadoId")
	List<Venta> findByEstadoId(@Param("estadoId") Integer estadoId);
	
	@Query("SELECT v FROM Venta v WHERE v.metodoPago.id = :metodoPagoId")
	List<Venta> findByMetodoPagoId(@Param("metodoPagoId") Integer metodoPagoId);
	
	@Query("SELECT v FROM Venta v WHERE v.metodoEnvio.id = :metodoEnvioId")
	List<Venta> findByMetodoEnvioId(@Param("metodoEnvioId") Integer metodoEnvioId);
	
	@Query("SELECT v FROM Venta v WHERE v.numeroVenta = :numeroVenta")
	Venta findByNumeroVenta(@Param("numeroVenta") String numeroVenta);
	
	@Query("SELECT v FROM Venta v WHERE v.estado.nombre = 'PENDIENTE' ORDER BY v.id DESC")
	List<Venta> findPendingVentas();
	
	@Query("SELECT v FROM Venta v WHERE v.estado.nombre = 'ENTREGADO' ORDER BY v.id DESC")
	List<Venta> findDeliveredVentas();
	
	@Query("SELECT COUNT(v) FROM Venta v WHERE v.usuario.id = :usuarioId")
	int countVentasByUsuario(@Param("usuarioId") Integer usuarioId);
	
	@Query("SELECT v FROM Venta v WHERE v.usuario.id = :usuarioId AND v.estado.id = :estadoId")
	List<Venta> findByUsuarioIdAndEstadoId(@Param("usuarioId") Integer usuarioId, @Param("estadoId") Integer estadoId);

}
