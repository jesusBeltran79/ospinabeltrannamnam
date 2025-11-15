package co.edu.unbosque.ospinabeltrannamnam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.ospinabeltrannamnam.dto.RecetaDTO;
import co.edu.unbosque.ospinabeltrannamnam.service.RecetaService;

@RestController
@RequestMapping("/api/recetas")
@CrossOrigin(origins = "*")
public class RecetaController {

    @Autowired
    private RecetaService service;

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody RecetaDTO dto) {
        int res = service.create(dto);
        if (res != 0) return ResponseEntity.badRequest().body("No se pudo crear la receta. Verifique los ingredientes.");
        return ResponseEntity.ok("Receta creada correctamente.");
    }

    @GetMapping
    public ResponseEntity<List<RecetaDTO>> listar() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        RecetaDTO dto = service.getById(id);
        if (dto == null) return ResponseEntity.badRequest().body("Receta no encontrada.");
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody RecetaDTO dto) {
        int res = service.updateById(id, dto);
        if (res != 0) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Receta actualizada correctamente.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        int res = service.deleteById(id);
        if (res != 0) return ResponseEntity.badRequest().body("No existe la receta especificada.");
        return ResponseEntity.ok("Receta eliminada correctamente.");
    }
}
