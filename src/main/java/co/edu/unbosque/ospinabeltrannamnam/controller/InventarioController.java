package co.edu.unbosque.ospinabeltrannamnam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.ospinabeltrannamnam.dto.InventarioDTO;
import co.edu.unbosque.ospinabeltrannamnam.service.InventarioService;

@RestController
@RequestMapping("/api/inventarios")
@CrossOrigin(origins = "*")
public class InventarioController {

    @Autowired
    private InventarioService service;

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody InventarioDTO dto) {
        int res = service.create(dto);
        if (res != 0) return ResponseEntity.badRequest().body("No se pudo crear el inventario.");
        return ResponseEntity.ok("Inventario creado correctamente.");
    }

    @GetMapping
    public ResponseEntity<List<InventarioDTO>> listar() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        InventarioDTO dto = service.getById(id);
        if (dto == null) return ResponseEntity.badRequest().body("Inventario no encontrado.");
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody InventarioDTO dto) {
        int res = service.updateById(id, dto);
        if (res != 0) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Inventario actualizado correctamente.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        int res = service.deleteById(id);
        if (res != 0) return ResponseEntity.badRequest().body("No existe el inventario especificado.");
        return ResponseEntity.ok("Inventario eliminado correctamente.");
    }
}
