package Proyecto_EFA.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import Proyecto_EFA.demo.model.Producto;
import Proyecto_EFA.demo.service.ProductoService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/with-image")
    public ResponseEntity<?> createProductoWithImage(
        @RequestPart("producto") Producto producto,
        @RequestPart("file") MultipartFile file
    ) {
        try {
            Producto createdProducto = productoService.createProductoWithImage(producto, file);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProducto);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al subir la imagen a Cloudinary o al crear el producto: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getProductosFiltrados(
        @RequestParam(required = false) String categoria,
        @RequestParam(required = false) String genero
    ) {
        if (categoria != null || genero != null) {
            List<Producto> productosFiltrados = productoService.findProductosByFilters(categoria, genero);
            return ResponseEntity.ok(productosFiltrados);
        }
        return ResponseEntity.ok(productoService.getAllProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Integer id) {
        Producto producto = productoService.getProductoById(id);
        return producto != null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto createdProducto = productoService.createProducto(producto);
        return ResponseEntity.status(201).body(createdProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Integer id, @RequestBody Producto productoDetails) {
        Producto updated = productoService.updateProducto(id, productoDetails);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Producto> partialUpdateProducto(@PathVariable Integer id, @RequestBody Producto productoDetails) {
        Producto updated = productoService.updateProducto(id, productoDetails);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Integer id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/marca/{marcaId}")
    public ResponseEntity<List<Producto>> getProductosByMarca(@PathVariable Integer marcaId) {
        return ResponseEntity.ok(productoService.getProductosByMarca(marcaId));
    }

    @GetMapping("/buscar/categoria/{categoriaId}")
    public ResponseEntity<List<Producto>> getProductosByCategoria(@PathVariable Integer categoriaId) {
        return ResponseEntity.ok(productoService.getProductosByCategoria(categoriaId));
    }

    @GetMapping("/buscar/color/{colorId}")
    public ResponseEntity<List<Producto>> getProductosByColor(@PathVariable Integer colorId) {
        return ResponseEntity.ok(productoService.getProductosByColor(colorId));
    }

    @GetMapping("/buscar/material/{materialId}")
    public ResponseEntity<List<Producto>> getProductosByMaterial(@PathVariable Integer materialId) {
        return ResponseEntity.ok(productoService.getProductosByMaterial(materialId));
    }

    @GetMapping("/buscar/talla/{tallaId}")
    public ResponseEntity<List<Producto>> getProductosByTalla(@PathVariable Integer tallaId) {
        return ResponseEntity.ok(productoService.getProductosByTalla(tallaId));
    }

    @GetMapping("/buscar/rango-precio")
    public ResponseEntity<List<Producto>> getProductosByPriceRange(
            @RequestParam Double precioMin,
            @RequestParam Double precioMax) {
        return ResponseEntity.ok(productoService.getProductosByPriceRange(precioMin, precioMax));
    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<Producto>> searchProductosByNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(productoService.searchProductosByNombre(nombre));
    }

    @GetMapping("/buscar/marca/{marcaId}/categoria/{categoriaId}")
    public ResponseEntity<List<Producto>> getProductosByMarcaAndCategoria(
            @PathVariable Integer marcaId,
            @PathVariable Integer categoriaId) {
        return ResponseEntity.ok(productoService.getProductosByMarcaAndCategoria(marcaId, categoriaId));
    }

    @GetMapping("/top/mas-caros")
    public ResponseEntity<List<Producto>> getTop10MostExpensiveProducts() {
        return ResponseEntity.ok(productoService.getTop10MostExpensiveProducts());
    }

    @GetMapping("/top/mas-baratos")
    public ResponseEntity<List<Producto>> getTop10CheapestProducts() {
        return ResponseEntity.ok(productoService.getTop10CheapestProducts());
    }

    @GetMapping("/top/mas-caros/{limit}")
    public ResponseEntity<List<Producto>> getTopMostExpensiveProducts(@PathVariable int limit) {
        return ResponseEntity.ok(productoService.getTop10MostExpensiveProducts());
    }

    @GetMapping("/buscar/stock")
    public ResponseEntity<List<Producto>> getProductosConStock() {
        return ResponseEntity.ok(productoService.getProductosConStock());
    }

    @GetMapping("/buscar/codigo/{codigo}")
    public ResponseEntity<Producto> getProductoByCodigo(@PathVariable String codigo) {
        return productoService.getProductoByCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
