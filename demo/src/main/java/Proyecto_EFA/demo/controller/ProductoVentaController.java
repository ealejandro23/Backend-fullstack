package Proyecto_EFA.demo.controller;

import Proyecto_EFA.demo.model.ProductoVenta;
import Proyecto_EFA.demo.service.ProductoVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/producto-ventas")
public class ProductoVentaController {
    
    @Autowired
    private ProductoVentaService productoVentaService;

    @GetMapping
    public ResponseEntity<List<ProductoVenta>> getAllProductoVentas() {
        List<ProductoVenta> list = productoVentaService.getAllProductoVentas();
        return ResponseEntity.ok(list);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductoVenta> getProductoVentaById(@PathVariable Long id) {
        Optional<ProductoVenta> productoVenta = productoVentaService.getProductoVentaById(id);
        return productoVenta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<ProductoVenta> createProductoVenta(@RequestBody ProductoVenta productoVenta) {
        ProductoVenta created = productoVentaService.createProductoVenta(productoVenta);
        return ResponseEntity.ok(created);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProductoVenta> updateProductoVenta(@PathVariable Long id, @RequestBody ProductoVenta productoVenta) {
        ProductoVenta updated = productoVentaService.updateProductoVenta(id, productoVenta);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductoVenta(@PathVariable Long id) {
        productoVentaService.deleteProductoVenta(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/venta/{ventaId}")
    public ResponseEntity<List<ProductoVenta>> getProductoVentasByVenta(@PathVariable Long ventaId) {
        List<ProductoVenta> list = productoVentaService.getProductoVentasByVenta(ventaId);
        return ResponseEntity.ok(list);
    }
    
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<ProductoVenta>> getProductoVentasByProducto(@PathVariable Integer productoId) {
        List<ProductoVenta> list = productoVentaService.getProductoVentasByProducto(productoId);
        return ResponseEntity.ok(list);
    }
    
    @GetMapping("/venta/{ventaId}/producto/{productoId}")
    public ResponseEntity<ProductoVenta> getProductoVentaByVentaAndProducto(@PathVariable Long ventaId, @PathVariable Integer productoId) {
        ProductoVenta pv = productoVentaService.getProductoVentaByVentaAndProducto(ventaId, productoId);
        if (pv != null) {
            return ResponseEntity.ok(pv);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/producto/{productoId}/cantidad-vendida")
    public ResponseEntity<Integer> getTotalQuantitySoldByProducto(@PathVariable Integer productoId) {
        Integer total = productoVentaService.getTotalQuantitySoldByProducto(productoId);
        return ResponseEntity.ok(total);
    }
    
    @GetMapping("/producto/{productoId}/ingresos")
    public ResponseEntity<Double> getTotalRevenueByProducto(@PathVariable Integer productoId) {
        Double total = productoVentaService.getTotalRevenueByProducto(productoId);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/top-vendidos")
    public ResponseEntity<List<Object[]>> getTopSellingProducts(@RequestParam(defaultValue = "10") int limit) {
        List<Object[]> topProducts = productoVentaService.getTopSellingProducts(limit);
        return ResponseEntity.ok(topProducts);
    }

    @GetMapping("/venta/{ventaId}/contar-items")
    public ResponseEntity<Integer> countItemsByVenta(@PathVariable Long ventaId) {
        Integer count = productoVentaService.countItemsByVenta(ventaId);
        return ResponseEntity.ok(count);
    }
}
