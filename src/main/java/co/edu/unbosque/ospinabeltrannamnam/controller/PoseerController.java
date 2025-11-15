package co.edu.unbosque.ospinabeltrannamnam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.ospinabeltrannamnam.dto.PoseerDTO;
import co.edu.unbosque.ospinabeltrannamnam.service.PoseerService;

@RestController
@RequestMapping("/api/poseer")
@CrossOrigin(origins = "*")
public class PoseerController {

    @Autowired
    private PoseerService service;

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody PoseerDTO dto) {
        int res = service.create(dto);
        if (res != 0) return ResponseEntity.badRequest().body("No se pudo crear la relación poseer.");
        return ResponseEntity.ok("Relación poseer creada correctamente.");
    }

    @GetMapping
    public ResponseEntity<List<PoseerDTO>> listar() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        PoseerDTO dto = service.getById(id);
        if (dto == null) return ResponseEntity.badRequest().body("Registro poseer no encontrado.");
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody PoseerDTO dto) {
        int res = service.updateById(id, dto);
        if (res != 0) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Relación poseer actualizada correctamente.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        int res = service.deleteById(id);
        if (res != 0) return ResponseEntity.badRequest().body("No existe el registro poseer especificado.");
        return ResponseEntity.ok("Registro poseer eliminado correctamente.");
    }
}
