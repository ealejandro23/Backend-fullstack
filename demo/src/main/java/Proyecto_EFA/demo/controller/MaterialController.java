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

import Proyecto_EFA.demo.model.Materiales;
import Proyecto_EFA.demo.service.MaterialesService;

@RestController
@RequestMapping("/api/v1/materiales")
public class MaterialController {

    @Autowired
    private MaterialesService materialesService;

    @GetMapping
    public ResponseEntity<List<Materiales>> getAllMateriales() {
        return ResponseEntity.ok(materialesService.getAllMateriales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materiales> getMaterialById(@PathVariable Integer id) {
        Materiales materiales = materialesService.getMaterialById(id);
        return materiales != null ? ResponseEntity.ok(materiales) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Materiales> createMaterial(@RequestBody Materiales materiales) {
        Materiales createdMaterial = materialesService.createMaterial(materiales);
        return ResponseEntity.status(201).body(createdMaterial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materiales> updateMaterial(@PathVariable Integer id, @RequestBody Materiales materialesDetails) {
        Materiales updated = materialesService.updateMaterial(id, materialesDetails);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Integer id) {
        materialesService.deleteMaterial(id);
        return ResponseEntity.noContent().build();
    }
}