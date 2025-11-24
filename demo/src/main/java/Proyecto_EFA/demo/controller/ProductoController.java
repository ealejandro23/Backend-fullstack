package Proyecto_EFA.demo.controller;

import Proyecto_EFA.demo.model.Producto;
import Proyecto_EFA.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/productos")
@CrossOrigin("*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // LISTAR TODOS
    @GetMapping
    public ResponseEntity<Iterable<Producto>> getAll() {
        return ResponseEntity.ok(productoService.getAllProductos());
    }

    // OBTENER POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable Integer id) {
        Producto producto = productoService.getProductoById(id);
        return (producto != null) ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    // CREAR PRODUCTO CON IMAGEN
    @PostMapping
    public ResponseEntity<?> createProducto(
            @RequestPart("producto") Producto producto,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) {

        try {
            Producto creado = productoService.createProductoWithImage(producto, imagen);
            return ResponseEntity.ok(creado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al subir imagen o guardar producto: " + e.getMessage());
        }
    }

    // ACTUALIZAR PRODUCTO
    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(
            @PathVariable Integer id,
            @RequestBody Producto updated) {

        Producto modificado = productoService.updateProducto(id, updated);
        return (modificado != null) ? ResponseEntity.ok(modificado) : ResponseEntity.notFound().build();
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}
