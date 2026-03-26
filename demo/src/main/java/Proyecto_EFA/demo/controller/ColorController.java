package Proyecto_EFA.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Proyecto_EFA.demo.model.Colores;
import Proyecto_EFA.demo.service.ColoresService;

@RestController
@RequestMapping("/api/v1/colores")
public class ColorController {

    @Autowired
    private ColoresService coloresService;

    @GetMapping
    public ResponseEntity<List<Colores>> getAllColores() {
        return ResponseEntity.ok(coloresService.getAllColores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colores> getColorById(@PathVariable Integer id) {
        Colores colores = coloresService.getColorById(id);
        return colores != null ? ResponseEntity.ok(colores) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Colores> createColor(@RequestBody Colores colores) {
        Colores createdColor = coloresService.createColor(colores);
        return ResponseEntity.status(201).body(createdColor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Colores> updateColor(@PathVariable Integer id, @RequestBody Colores coloresDetails) {
        Colores updated = coloresService.updateColor(id, coloresDetails);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable Integer id) {
        coloresService.deleteColor(id);
        return ResponseEntity.noContent().build();
    }
}