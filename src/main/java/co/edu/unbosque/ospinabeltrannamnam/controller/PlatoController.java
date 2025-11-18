package co.edu.unbosque.ospinabeltrannamnam.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unbosque.ospinabeltrannamnam.dto.PlatoDTO;
import co.edu.unbosque.ospinabeltrannamnam.service.PlatoService;

@RestController
@RequestMapping("/api/platos")
@CrossOrigin(origins = "*")
public class PlatoController {

	@Autowired
	private PlatoService service;

	@PostMapping
	public ResponseEntity<String> crear(@RequestBody PlatoDTO dto) {
		int res = service.create(dto);
		if (res != 0)
			return ResponseEntity.badRequest().body("No se pudo crear el plato.");
		return ResponseEntity.ok("Plato creado correctamente.");
	}

	@GetMapping
	public ResponseEntity<List<PlatoDTO>> listar() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtener(@PathVariable Integer id) {
		PlatoDTO dto = service.getById(id);
		if (dto == null)
			return ResponseEntity.badRequest().body("Plato no encontrado.");
		return ResponseEntity.ok(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody PlatoDTO dto) {
		int res = service.updateById(id, dto);
		if (res != 0)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok("Plato actualizado correctamente.");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Integer id) {
		int res = service.deleteById(id);
		if (res != 0)
			return ResponseEntity.badRequest().body("No existe el plato especificado.");
		return ResponseEntity.ok("Plato eliminado correctamente.");
	}
}
