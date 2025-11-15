package co.edu.unbosque.ospinabeltrannamnam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.ospinabeltrannamnam.dto.OrdenCompraDTO;
import co.edu.unbosque.ospinabeltrannamnam.service.OrdenCompraService;

@RestController
@RequestMapping("/api/ordenes")
@CrossOrigin(origins = "*")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraService service;

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody OrdenCompraDTO dto) {
        int res = service.create(dto);
        if (res != 0) return ResponseEntity.badRequest().body("No se pudo crear la orden de compra.");
        return ResponseEntity.ok("Orden de compra creada correctamente.");
    }

    @GetMapping
    public ResponseEntity<List<OrdenCompraDTO>> listar() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        OrdenCompraDTO dto = service.getById(id);
        if (dto == null) return ResponseEntity.badRequest().body("Orden de compra no encontrada.");
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody OrdenCompraDTO dto) {
        int res = service.updateById(id, dto);
        if (res != 0) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Orden de compra actualizada correctamente.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        int res = service.deleteById(id);
        if (res != 0) return ResponseEntity.badRequest().body("No existe la orden de compra especificada.");
        return ResponseEntity.ok("Orden de compra eliminada correctamente.");
    }
}
