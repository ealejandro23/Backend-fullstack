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

import Proyecto_EFA.demo.model.Estado;
import Proyecto_EFA.demo.service.EstadoService;

@RestController
@RequestMapping("/api/v1/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public ResponseEntity<List<Estado>> getAllEstados() {
        return ResponseEntity.ok(estadoService.getAllEstados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> getEstadoById(@PathVariable Integer id) {
        Estado estado = estadoService.getEstadoById(id);
        return estado != null ? ResponseEntity.ok(estado) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Estado> createEstado(@RequestBody Estado estado) {
        Estado createdEstado = estadoService.createEstado(estado);
        return ResponseEntity.status(201).body(createdEstado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> updateEstado(@PathVariable Integer id, @RequestBody Estado estadoDetails) {
        Estado updated = estadoService.updateEstado(id, estadoDetails);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstado(@PathVariable Integer id) {
        estadoService.deleteEstado(id);
        return ResponseEntity.noContent().build();
    }
}