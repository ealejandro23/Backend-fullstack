package Proyecto_EFA.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyecto_EFA.demo.model.MetodoEnvio;
import Proyecto_EFA.demo.service.MetodoEnvioService;

@RestController
@RequestMapping("/api/v1/metodos-envio")
public class MetodoEnvioController {

    @Autowired
    private MetodoEnvioService metodoEnvioService;

    // GET /api/v1/metodos-envio
    @GetMapping
    public ResponseEntity<List<MetodoEnvio>> getAllMetodosEnvio() {
        return ResponseEntity.ok(metodoEnvioService.getAllMetodosEnvio());
    }

    // GET /api/v1/metodos-envio/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MetodoEnvio> getMetodoEnvioById(@PathVariable Integer id) {
        MetodoEnvio metodoEnvio = metodoEnvioService.getMetodoEnvioById(id);
        return metodoEnvio != null ? ResponseEntity.ok(metodoEnvio) : ResponseEntity.notFound().build();
    }

    // GET /api/v1/metodos-envio/nombre/{nombre}
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<MetodoEnvio> getMetodoEnvioByNombre(@PathVariable String nombre) {
        MetodoEnvio metodoEnvio = metodoEnvioService.getMetodoEnvioByNombre(nombre);
        return metodoEnvio != null ? ResponseEntity.ok(metodoEnvio) : ResponseEntity.notFound().build();
    }

    // POST /api/v1/metodos-envio
    @PostMapping
    public ResponseEntity<?> createMetodoEnvio(@RequestBody MetodoEnvio metodoEnvio) {
        try {
            MetodoEnvio created = metodoEnvioService.createMetodoEnvio(metodoEnvio);
            return ResponseEntity.status(201).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PUT /api/v1/metodos-envio/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMetodoEnvio(@PathVariable Integer id, @RequestBody MetodoEnvio metodoEnvioDetails) {
        try {
            MetodoEnvio updated = metodoEnvioService.updateMetodoEnvio(id, metodoEnvioDetails);
            return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PATCH /api/v1/metodos-envio/{id}
    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdateMetodoEnvio(@PathVariable Integer id, @RequestBody MetodoEnvio metodoEnvioDetails) {
        try {
            MetodoEnvio updated = metodoEnvioService.partialUpdateMetodoEnvio(id, metodoEnvioDetails);
            return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE /api/v1/metodos-envio/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMetodoEnvio(@PathVariable Integer id) {
        try {
            metodoEnvioService.deleteMetodoEnvio(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}