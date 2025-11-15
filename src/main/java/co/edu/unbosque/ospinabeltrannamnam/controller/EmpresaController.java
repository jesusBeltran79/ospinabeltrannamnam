package co.edu.unbosque.ospinabeltrannamnam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.ospinabeltrannamnam.dto.EmpresaDTO;
import co.edu.unbosque.ospinabeltrannamnam.service.EmpresaService;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*")
public class EmpresaController {

    @Autowired
    private EmpresaService service;

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody EmpresaDTO dto) {
        int res = service.create(dto);
        if (res != 0) return ResponseEntity.badRequest().body("No se pudo crear la empresa.");
        return ResponseEntity.ok("Empresa creada correctamente.");
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> listar() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{nit}")
    public ResponseEntity<?> obtener(@PathVariable Integer nit) {
        EmpresaDTO dto = service.getById(nit);
        if (dto == null) return ResponseEntity.badRequest().body("Empresa no encontrada.");
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{nit}")
    public ResponseEntity<String> actualizar(@PathVariable Integer nit, @RequestBody EmpresaDTO dto) {
        int res = service.updateById(nit, dto);
        if (res != 0) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Empresa actualizada correctamente.");
    }

    @DeleteMapping("/{nit}")
    public ResponseEntity<String> eliminar(@PathVariable Integer nit) {
        int res = service.deleteById(nit);
        if (res != 0) return ResponseEntity.badRequest().body("No existe la empresa especificada.");
        return ResponseEntity.ok("Empresa eliminada correctamente.");
    }
}
