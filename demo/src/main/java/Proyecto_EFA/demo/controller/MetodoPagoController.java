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
import Proyecto_EFA.demo.model.MetodoPago;
import Proyecto_EFA.demo.service.MetodoPagoService;

@RestController
@RequestMapping("/api/v1/metodos-pago")
<<<<<<< HEAD
@CrossOrigin(origins = "*")
=======
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping
    public ResponseEntity<List<MetodoPago>> getAllMetodosPago() {
        return ResponseEntity.ok(metodoPagoService.getAllMetodosPago());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> getMetodoPagoById(@PathVariable Integer id) {
        MetodoPago metodoPago = metodoPagoService.getMetodoPagoById(id);
        return metodoPago != null ? ResponseEntity.ok(metodoPago) : ResponseEntity.notFound().build();
    }

<<<<<<< HEAD
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<MetodoPago> getMetodoPagoByNombre(@PathVariable String nombre) {
        MetodoPago metodoPago = metodoPagoService.getMetodoPagoByNombre(nombre);
        return metodoPago != null ? ResponseEntity.ok(metodoPago) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createMetodoPago(@RequestBody MetodoPago metodoPago) {
        try {
            MetodoPago created = metodoPagoService.createMetodoPago(metodoPago);
            return ResponseEntity.status(201).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMetodoPago(@PathVariable Integer id, @RequestBody MetodoPago metodoPagoDetails) {
        try {
            MetodoPago updated = metodoPagoService.updateMetodoPago(id, metodoPagoDetails);
            return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdateMetodoPago(@PathVariable Integer id, @RequestBody MetodoPago metodoPagoDetails) {
        try {
            MetodoPago updated = metodoPagoService.partialUpdateMetodoPago(id, metodoPagoDetails);
            return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMetodoPago(@PathVariable Integer id) {
        try {
            metodoPagoService.deleteMetodoPago(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
=======
    @PostMapping
    public ResponseEntity<MetodoPago> createMetodoPago(@RequestBody MetodoPago metodoPago) {
        MetodoPago createdMetodoPago = metodoPagoService.createMetodoPago(metodoPago);
        return ResponseEntity.status(201).body(createdMetodoPago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPago> updateMetodoPago(@PathVariable Integer id, @RequestBody MetodoPago metodoPagoDetails) {
        MetodoPago updated = metodoPagoService.updateMetodoPago(id, metodoPagoDetails);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMetodoPago(@PathVariable Integer id) {
        metodoPagoService.deleteMetodoPago(id);
        return ResponseEntity.noContent().build();
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
    }
}