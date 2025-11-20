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

import Proyecto_EFA.demo.model.Venta;
import Proyecto_EFA.demo.service.VentaService;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Venta>> getAllVentas() {
        return ResponseEntity.ok(ventaService.getAllVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Long id) {
        Venta venta = ventaService.getVentaById(id);
        return venta != null ? ResponseEntity.ok(venta) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Venta> createVenta(@RequestBody Venta venta) {
        Venta createdVenta = ventaService.createVenta(venta);
        return ResponseEntity.status(201).body(createdVenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> updateVenta(@PathVariable Long id, @RequestBody Venta ventaDetails) {
        Venta updated = ventaService.updateVenta(id, ventaDetails);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Venta> partialUpdateVenta(@PathVariable Long id, @RequestBody Venta ventaDetails) {
        Venta updated = ventaService.updateVenta(id, ventaDetails);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
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