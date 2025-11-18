package co.edu.unbosque.ospinabeltrannamnam.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<?> crear(@RequestBody InventarioDTO dto) {
		try {
			Integer id = service.create(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("inventarioId", id));
		} catch (RuntimeException ex) {
			return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
		}
	}

	@GetMapping
	public ResponseEntity<List<InventarioDTO>> listar() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtener(@PathVariable Integer id) {
		InventarioDTO dto = service.getById(id);
		if (dto == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody InventarioDTO dto) {
		int res = service.updateById(id, dto);
		if (res != 0)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(Map.of("message", "Inventario actualizado correctamente."));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer id) {
		int res = service.deleteById(id);
		if (res != 0)
			return ResponseEntity.badRequest().body("No existe el inventario especificado.");
		return ResponseEntity.ok(Map.of("message", "Inventario eliminado correctamente."));
	}
}
