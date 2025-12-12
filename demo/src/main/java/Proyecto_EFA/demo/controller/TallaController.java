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

import Proyecto_EFA.demo.model.Tallas;
import Proyecto_EFA.demo.service.TallasService;

@RestController
@RequestMapping("/api/v1/tallas")
public class TallaController {

    @Autowired
    private TallasService tallasService;

    @GetMapping
    public ResponseEntity<List<Tallas>> getAllTallas() {
        return ResponseEntity.ok(tallasService.getAllTallas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tallas> getTallaById(@PathVariable Integer id) {
        Tallas talla = tallasService.getTallaById(id);
        return talla != null ? ResponseEntity.ok(talla) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Tallas> createTalla(@RequestBody Tallas tallas) {
        Tallas createdTalla = tallasService.createTalla(tallas);
        return ResponseEntity.status(201).body(createdTalla);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tallas> updateTalla(@PathVariable Integer id, @RequestBody Tallas tallasDetails) {
        Tallas updated = tallasService.updateTalla(id, tallasDetails);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTalla(@PathVariable Integer id) {
        tallasService.deleteTalla(id);
        return ResponseEntity.noContent().build();
    }
}