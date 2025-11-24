package Proyecto_EFA.demo.controller;

import java.util.List;
<<<<<<< HEAD
=======
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
=======
>>>>>>> 4a3ce949aed30a17f12c82f5f78878496ed9c224

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PatchMapping;
=======
>>>>>>> 4a3ce949aed30a17f12c82f5f78878496ed9c224
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
=======
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
>>>>>>> 4a3ce949aed30a17f12c82f5f78878496ed9c224
import Proyecto_EFA.demo.model.MetodoEnvio;
import Proyecto_EFA.demo.service.MetodoEnvioService;

@RestController
@RequestMapping("/api/v1/metodos-envio")
<<<<<<< HEAD
=======
<<<<<<< HEAD
@CrossOrigin(origins = "*")
=======
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
>>>>>>> 4a3ce949aed30a17f12c82f5f78878496ed9c224
public class MetodoEnvioController {

    @Autowired
    private MetodoEnvioService metodoEnvioService;

<<<<<<< HEAD
    // GET /api/v1/metodos-envio
=======
>>>>>>> 4a3ce949aed30a17f12c82f5f78878496ed9c224
    @GetMapping
    public ResponseEntity<List<MetodoEnvio>> getAllMetodosEnvio() {
        return ResponseEntity.ok(metodoEnvioService.getAllMetodosEnvio());
    }

<<<<<<< HEAD
    // GET /api/v1/metodos-envio/{id}
=======
>>>>>>> 4a3ce949aed30a17f12c82f5f78878496ed9c224
    @GetMapping("/{id}")
    public ResponseEntity<MetodoEnvio> getMetodoEnvioById(@PathVariable Integer id) {
        MetodoEnvio metodoEnvio = metodoEnvioService.getMetodoEnvioById(id);
        return metodoEnvio != null ? ResponseEntity.ok(metodoEnvio) : ResponseEntity.notFound().build();
    }

<<<<<<< HEAD
    // GET /api/v1/metodos-envio/nombre/{nombre}
=======
<<<<<<< HEAD
>>>>>>> 4a3ce949aed30a17f12c82f5f78878496ed9c224
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<MetodoEnvio> getMetodoEnvioByNombre(@PathVariable String nombre) {
        MetodoEnvio metodoEnvio = metodoEnvioService.getMetodoEnvioByNombre(nombre);
        return metodoEnvio != null ? ResponseEntity.ok(metodoEnvio) : ResponseEntity.notFound().build();
    }

<<<<<<< HEAD
    // POST /api/v1/metodos-envio
=======
>>>>>>> 4a3ce949aed30a17f12c82f5f78878496ed9c224
    @PostMapping
    public ResponseEntity<?> createMetodoEnvio(@RequestBody MetodoEnvio metodoEnvio) {
        try {
            MetodoEnvio created = metodoEnvioService.createMetodoEnvio(metodoEnvio);
            return ResponseEntity.status(201).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

<<<<<<< HEAD
    // PUT /api/v1/metodos-envio/{id}
=======
>>>>>>> 4a3ce949aed30a17f12c82f5f78878496ed9c224
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMetodoEnvio(@PathVariable Integer id, @RequestBody MetodoEnvio metodoEnvioDetails) {
        try {
            MetodoEnvio updated = metodoEnvioService.updateMetodoEnvio(id, metodoEnvioDetails);
            return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

<<<<<<< HEAD
    // PATCH /api/v1/metodos-envio/{id}
=======
>>>>>>> 4a3ce949aed30a17f12c82f5f78878496ed9c224
    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdateMetodoEnvio(@PathVariable Integer id, @RequestBody MetodoEnvio metodoEnvioDetails) {
        try {
            MetodoEnvio updated = metodoEnvioService.partialUpdateMetodoEnvio(id, metodoEnvioDetails);
            return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

<<<<<<< HEAD
    // DELETE /api/v1/metodos-envio/{id}
=======
>>>>>>> 4a3ce949aed30a17f12c82f5f78878496ed9c224
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMetodoEnvio(@PathVariable Integer id) {
        try {
            metodoEnvioService.deleteMetodoEnvio(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
<<<<<<< HEAD
=======
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
>>>>>>> 4a3ce949aed30a17f12c82f5f78878496ed9c224
    }
}