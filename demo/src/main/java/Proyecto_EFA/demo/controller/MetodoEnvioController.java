package Proyecto_EFA.demo.controller;

import java.util.List;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
=======

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

>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
import Proyecto_EFA.demo.model.MetodoEnvio;
import Proyecto_EFA.demo.service.MetodoEnvioService;

@RestController
@RequestMapping("/api/v1/metodos-envio")
<<<<<<< HEAD
@CrossOrigin(origins = "*")
=======
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
public class MetodoEnvioController {

    @Autowired
    private MetodoEnvioService metodoEnvioService;

    @GetMapping
    public ResponseEntity<List<MetodoEnvio>> getAllMetodosEnvio() {
        return ResponseEntity.ok(metodoEnvioService.getAllMetodosEnvio());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoEnvio> getMetodoEnvioById(@PathVariable Integer id) {
        MetodoEnvio metodoEnvio = metodoEnvioService.getMetodoEnvioById(id);
        return metodoEnvio != null ? ResponseEntity.ok(metodoEnvio) : ResponseEntity.notFound().build();
    }

<<<<<<< HEAD
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<MetodoEnvio> getMetodoEnvioByNombre(@PathVariable String nombre) {
        MetodoEnvio metodoEnvio = metodoEnvioService.getMetodoEnvioByNombre(nombre);
        return metodoEnvio != null ? ResponseEntity.ok(metodoEnvio) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createMetodoEnvio(@RequestBody MetodoEnvio metodoEnvio) {
        try {
            MetodoEnvio created = metodoEnvioService.createMetodoEnvio(metodoEnvio);
            return ResponseEntity.status(201).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMetodoEnvio(@PathVariable Integer id, @RequestBody MetodoEnvio metodoEnvioDetails) {
        try {
            MetodoEnvio updated = metodoEnvioService.updateMetodoEnvio(id, metodoEnvioDetails);
            return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdateMetodoEnvio(@PathVariable Integer id, @RequestBody MetodoEnvio metodoEnvioDetails) {
        try {
            MetodoEnvio updated = metodoEnvioService.partialUpdateMetodoEnvio(id, metodoEnvioDetails);
            return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMetodoEnvio(@PathVariable Integer id) {
        try {
            metodoEnvioService.deleteMetodoEnvio(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
=======
    @PostMapping
    public ResponseEntity<MetodoEnvio> createMetodoEnvio(@RequestBody MetodoEnvio metodoEnvio) {
        MetodoEnvio createdMetodoEnvio = metodoEnvioService.createMetodoEnvio(metodoEnvio);
        return ResponseEntity.status(201).body(createdMetodoEnvio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoEnvio> updateMetodoEnvio(@PathVariable Integer id, @RequestBody MetodoEnvio metodoEnvioDetails) {
        MetodoEnvio updated = metodoEnvioService.updateMetodoEnvio(id, metodoEnvioDetails);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMetodoEnvio(@PathVariable Integer id) {
        metodoEnvioService.deleteMetodoEnvio(id);
        return ResponseEntity.noContent().build();
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
    }
}