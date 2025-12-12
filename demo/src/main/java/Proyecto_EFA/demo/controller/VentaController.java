package Proyecto_EFA.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyecto_EFA.demo.dto.VentaRequest;
import Proyecto_EFA.demo.dto.ItemVentaRequest;
import Proyecto_EFA.demo.model.Venta;
import Proyecto_EFA.demo.model.Usuario;
import Proyecto_EFA.demo.model.Estado;
import Proyecto_EFA.demo.model.MetodoPago;
import Proyecto_EFA.demo.model.MetodoEnvio;
import Proyecto_EFA.demo.model.Producto;
import Proyecto_EFA.demo.model.ProductoVenta;
import Proyecto_EFA.demo.service.VentaService;
import Proyecto_EFA.demo.service.UsuarioService;
import Proyecto_EFA.demo.service.EstadoService;
import Proyecto_EFA.demo.service.MetodoPagoService;
import Proyecto_EFA.demo.service.MetodoEnvioService;
import Proyecto_EFA.demo.service.ProductoService;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private MetodoPagoService metodoPagoService;

    @Autowired
    private MetodoEnvioService metodoEnvioService;

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<?> createVenta(@RequestBody VentaRequest ventaRequest) {
        try {
            System.out.println("📦 Recibiendo solicitud de venta: " + ventaRequest);
            
            // 🔴 🔴 🔴 VALIDACIONES CRÍTICAS AGREGADAS 🔴 🔴 🔴
            if (ventaRequest.getUsuarioId() == null) {
                return ResponseEntity.badRequest().body("❌ ERROR: El ID de usuario es NULL");
            }
            if (ventaRequest.getEstadoId() == null) {
                return ResponseEntity.badRequest().body("❌ ERROR: El ID de estado es NULL");
            }
            if (ventaRequest.getMetodoPagoId() == null) {
                return ResponseEntity.badRequest().body("❌ ERROR: El ID de método de pago es NULL");
            }
            if (ventaRequest.getMetodoEnvioId() == null) {
                return ResponseEntity.badRequest().body("❌ ERROR: El ID de método de envío es NULL");
            }
            
            // Validar que hay items
            if (ventaRequest.getItems() == null || ventaRequest.getItems().isEmpty()) {
                return ResponseEntity.badRequest().body("❌ ERROR: La venta debe tener al menos un producto");
            }

            // 🔴 VALIDAR CADA ITEM INDIVIDUALMENTE
            for (int i = 0; i < ventaRequest.getItems().size(); i++) {
                ItemVentaRequest item = ventaRequest.getItems().get(i);
                if (item.getProductoId() == null) {
                    return ResponseEntity.badRequest().body("❌ ERROR: El producto en la posición " + i + " tiene ID NULL");
                }
                if (item.getCantidad() == null || item.getCantidad() <= 0) {
                    return ResponseEntity.badRequest().body("❌ ERROR: La cantidad del producto en la posición " + i + " es inválida");
                }
                if (item.getPrecioUnitario() == null || item.getPrecioUnitario() <= 0) {
                    return ResponseEntity.badRequest().body("❌ ERROR: El precio unitario del producto en la posición " + i + " es inválido");
                }
            }

            // DEBUG: Mostrar los IDs que estamos recibiendo
            System.out.println("🔍 DEBUG - IDs recibidos:");
            System.out.println("Usuario ID: " + ventaRequest.getUsuarioId());
            System.out.println("Estado ID: " + ventaRequest.getEstadoId());
            System.out.println("Método Pago ID: " + ventaRequest.getMetodoPagoId());
            System.out.println("Método Envío ID: " + ventaRequest.getMetodoEnvioId());
            System.out.println("Número de items: " + ventaRequest.getItems().size());

            // Crear la venta
            Venta venta = new Venta();
            
            // Generar número de venta único
            venta.setNumeroVenta("VEN-" + System.currentTimeMillis());
            
            // Buscar y asignar entidades relacionadas
            Usuario usuario = usuarioService.getUsuarioById(ventaRequest.getUsuarioId());
            if (usuario == null) {
                return ResponseEntity.badRequest().body("❌ ERROR: Usuario no encontrado con ID: " + ventaRequest.getUsuarioId());
            }
            venta.setUsuario(usuario);

            Estado estado = estadoService.getEstadoById(ventaRequest.getEstadoId());
            if (estado == null) {
                return ResponseEntity.badRequest().body("❌ ERROR: Estado no encontrado con ID: " + ventaRequest.getEstadoId());
            }
            venta.setEstado(estado);

            MetodoPago metodoPago = metodoPagoService.getMetodoPagoById(ventaRequest.getMetodoPagoId());
            if (metodoPago == null) {
                return ResponseEntity.badRequest().body("❌ ERROR: Método de pago no encontrado con ID: " + ventaRequest.getMetodoPagoId());
            }
            venta.setMetodoPago(metodoPago);

            MetodoEnvio metodoEnvio = metodoEnvioService.getMetodoEnvioById(ventaRequest.getMetodoEnvioId());
            if (metodoEnvio == null) {
                return ResponseEntity.badRequest().body("❌ ERROR: Método de envío no encontrado con ID: " + ventaRequest.getMetodoEnvioId());
            }
            venta.setMetodoEnvio(metodoEnvio);

            //PROCESAR ITEMS DE LA VENTA
            for (ItemVentaRequest itemRequest : ventaRequest.getItems()) {
                Producto producto = productoService.getProductoById(itemRequest.getProductoId());
                if (producto == null) {
                    return ResponseEntity.badRequest().body("❌ ERROR: Producto no encontrado con ID: " + itemRequest.getProductoId());
                }

                // Crear ProductoVenta
                ProductoVenta productoVenta = new ProductoVenta();
                productoVenta.setVenta(venta);
                productoVenta.setProducto(producto);
                productoVenta.setCantidad(itemRequest.getCantidad());
                productoVenta.setPrecioUnitario(itemRequest.getPrecioUnitario());
                productoVenta.setSubtotal(itemRequest.getPrecioUnitario() * itemRequest.getCantidad());

                // Agregar a la venta
                venta.getItems().add(productoVenta);
            }

            System.out.println("✅ Venta creada con " + venta.getItems().size() + " productos");
            
            // Guardar la venta (esto activará el @PrePersist que calcula el total)
            Venta ventaCreada = ventaService.createVenta(venta);
            
            return ResponseEntity.status(201).body(ventaCreada);
            
        } catch (Exception e) {
            System.err.println("❌ ERROR CRÍTICO creando venta: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("❌ Error al crear la venta: " + e.getMessage());
        }
    }

    @PostMapping("/original")
    public ResponseEntity<Venta> createVentaOriginal(@RequestBody Venta venta) {
        Venta createdVenta = ventaService.createVenta(venta);
        return ResponseEntity.status(201).body(createdVenta);
    }

    @GetMapping
    public ResponseEntity<List<Venta>> getAllVentas() {
        return ResponseEntity.ok(ventaService.getAllVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Integer id) {
        Venta venta = ventaService.getVentaById(id);
        return venta != null ? ResponseEntity.ok(venta) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> updateVenta(@PathVariable Integer id, @RequestBody Venta ventaDetails) {
        Venta updated = ventaService.updateVenta(id, ventaDetails);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Venta> partialUpdateVenta(@PathVariable Integer id, @RequestBody Venta ventaDetails) {
        Venta updated = ventaService.updateVenta(id, ventaDetails);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Integer id) {
        ventaService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }
    
    // Búsquedas avanzadas
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Venta>> getVentasByUsuario(@PathVariable Integer usuarioId) {
        List<Venta> ventas = ventaService.getVentasByUsuario(usuarioId);
        return ResponseEntity.ok(ventas);
    }
    
    @GetMapping("/estado/{estadoId}")
    public ResponseEntity<List<Venta>> getVentasByEstado(@PathVariable Integer estadoId) {
        List<Venta> ventas = ventaService.getVentasByEstado(estadoId);
        return ResponseEntity.ok(ventas);
    }
    
    @GetMapping("/metodo-pago/{metodoPagoId}")
    public ResponseEntity<List<Venta>> getVentasByMetodoPago(@PathVariable Integer metodoPagoId) {
        List<Venta> ventas = ventaService.getVentasByMetodoPago(metodoPagoId);
        return ResponseEntity.ok(ventas);
    }
    
    @GetMapping("/metodo-envio/{metodoEnvioId}")
    public ResponseEntity<List<Venta>> getVentasByMetodoEnvio(@PathVariable Integer metodoEnvioId) {
        List<Venta> ventas = ventaService.getVentasByMetodoEnvio(metodoEnvioId);
        return ResponseEntity.ok(ventas);
    }
    
    @GetMapping("/numero/{numeroVenta}")
    public ResponseEntity<Venta> getVentaByNumeroVenta(@PathVariable String numeroVenta) {
        Venta venta = ventaService.getVentaByNumeroVenta(numeroVenta);
        return venta != null ? ResponseEntity.ok(venta) : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/pendientes")
    public ResponseEntity<List<Venta>> getPendingVentas() {
        List<Venta> ventas = ventaService.getPendingVentas();
        return ResponseEntity.ok(ventas);
    }
    
    @GetMapping("/entregadas")
    public ResponseEntity<List<Venta>> getDeliveredVentas() {
        List<Venta> ventas = ventaService.getDeliveredVentas();
        return ResponseEntity.ok(ventas);
    }
    
    @GetMapping("/usuario/{usuarioId}/estado/{estadoId}")
    public ResponseEntity<List<Venta>> getVentasByUsuarioAndEstado(@PathVariable Integer usuarioId, @PathVariable Integer estadoId) {
        List<Venta> ventas = ventaService.getVentasByUsuarioAndEstado(usuarioId, estadoId);
        return ResponseEntity.ok(ventas);
    }
    
    @GetMapping("/contar/usuario/{usuarioId}")
    public ResponseEntity<Integer> countVentasByUsuario(@PathVariable Integer usuarioId) {
        int count = ventaService.countVentasByUsuario(usuarioId);
        return ResponseEntity.ok(count);
    }
}
