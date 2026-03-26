package Proyecto_EFA.demo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import Proyecto_EFA.demo.model.Categorias;
import Proyecto_EFA.demo.service.CategoriasService;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

    @Autowired
    private CategoriasService categoriasService;

    @GetMapping
    public ResponseEntity<List<Categorias>> getAllCategorias() {
        List<Categorias> categorias = categoriasService.findAll();
        if (categorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorias> getCategoriaById(@PathVariable Integer id) {
        Categorias categorias = categoriasService.findById(id);
        if (categorias == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    public ResponseEntity<Categorias> createCategoria(@RequestBody Categorias categorias) {
        Categorias createdCategoria = categoriasService.save(categorias);
        return ResponseEntity.status(201).body(createdCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categorias> updateCategoria(@PathVariable Integer id, @RequestBody Categorias categorias) {
        categorias.setId(id);
        Categorias updated = categoriasService.partialUpdate(categorias);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Integer id) {
        categoriasService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}