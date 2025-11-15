package co.edu.unbosque.ospinabeltrannamnam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.ospinabeltrannamnam.dto.LoteDTO;
import co.edu.unbosque.ospinabeltrannamnam.service.LoteService;

@RestController
@RequestMapping("/api/lotes")
@CrossOrigin(origins = "*")
public class LoteController {

    @Autowired
    private LoteService service;

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody LoteDTO dto) {
        int res = service.create(dto);
        if (res != 0) return ResponseEntity.badRequest().body("No se pudo crear el lote.");
        return ResponseEntity.ok("Lote creado correctamente.");
    }

    @GetMapping
    public ResponseEntity<List<LoteDTO>> listar() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        LoteDTO dto = service.getById(id);
        if (dto == null) return ResponseEntity.badRequest().body("Lote no encontrado.");
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody LoteDTO dto) {
        int res = service.updateById(id, dto);
        if (res != 0) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Lote actualizado correctamente.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        int res = service.deleteById(id);
        if (res != 0) return ResponseEntity.badRequest().body("No existe el lote especificado.");
        return ResponseEntity.ok("Lote eliminado correctamente.");
    }
}
