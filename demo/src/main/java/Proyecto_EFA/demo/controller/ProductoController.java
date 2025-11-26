package Proyecto_EFA.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Proyecto_EFA.demo.model.Producto;
import Proyecto_EFA.demo.service.ProductoService;
import Proyecto_EFA.demo.dto.ProductoCreationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import Proyecto_EFA.demo.model.Imagen;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/{productoId}/images")
    public ResponseEntity<?> addImageToProducto(
        @PathVariable Integer productoId,
        @RequestPart("file") MultipartFile file     
    ) {
        try {
            Imagen nuevaImagen = productoService.addImageToProducto(productoId, file);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaImagen);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al subir la imagen o enlazar al producto: " + e.getMessage());
        }
    }

    @PostMapping 
    public ResponseEntity<Producto> createProducto(@RequestBody ProductoCreationDTO productoDto) {
        Producto createdProducto = productoService.createProducto(productoDto);
        return ResponseEntity.status(201).body(createdProducto);
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
        List<Producto> productos = productoService.getProductosByMarca(marcaId);
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/buscar/categoria/{categoriaId}")
    public ResponseEntity<List<Producto>> getProductosByCategoria(@PathVariable Integer categoriaId) {
        List<Producto> productos = productoService.getProductosByCategoria(categoriaId);
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/buscar/color/{colorId}")
    public ResponseEntity<List<Producto>> getProductosByColor(@PathVariable Integer colorId) {
        List<Producto> productos = productoService.getProductosByColor(colorId);
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/buscar/material/{materialId}")
    public ResponseEntity<List<Producto>> getProductosByMaterial(@PathVariable Integer materialId) {
        List<Producto> productos = productoService.getProductosByMaterial(materialId);
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/buscar/talla/{tallaId}")
    public ResponseEntity<List<Producto>> getProductosByTalla(@PathVariable Integer tallaId) {
        List<Producto> productos = productoService.getProductosByTalla(tallaId);
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/buscar/rango-precio")
    public ResponseEntity<List<Producto>> getProductosByPriceRange(
            @RequestParam Double precioMin,
            @RequestParam Double precioMax) {
        List<Producto> productos = productoService.getProductosByPriceRange(precioMin, precioMax);
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<Producto>> searchProductosByNombre(@RequestParam String nombre) {
        List<Producto> productos = productoService.searchProductosByNombre(nombre);
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/buscar/marca/{marcaId}/categoria/{categoriaId}")
    public ResponseEntity<List<Producto>> getProductosByMarcaAndCategoria(@PathVariable Integer marcaId, @PathVariable Integer categoriaId) {
        List<Producto> productos = productoService.getProductosByMarcaAndCategoria(marcaId, categoriaId);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/top/mas-caros")
    public ResponseEntity<List<Producto>> getTop
