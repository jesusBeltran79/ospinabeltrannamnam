package co.edu.unbosque.ospinabeltrannamnam.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unbosque.ospinabeltrannamnam.dto.IngredienteDTO;
import co.edu.unbosque.ospinabeltrannamnam.service.IngredienteService;

@RestController
@RequestMapping("/api/ingredientes")
@CrossOrigin(origins = "*")
public class IngredienteController {

	@Autowired
	private IngredienteService service;

	@PostMapping
	public ResponseEntity<String> crear(@RequestBody IngredienteDTO dto) {
		int res = service.create(dto);
		if (res != 0)
			return ResponseEntity.badRequest().body("No se pudo crear el ingrediente.");
		return ResponseEntity.ok("Ingrediente creado correctamente.");
	}

	@GetMapping
	public ResponseEntity<List<IngredienteDTO>> listar() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtener(@PathVariable Integer id) {
		IngredienteDTO dto = service.getById(id);
		if (dto == null)
			return ResponseEntity.badRequest().body("Ingrediente no encontrado.");
		return ResponseEntity.ok(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody IngredienteDTO dto) {
		int res = service.updateById(id, dto);
		if (res != 0)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok("Ingrediente actualizado correctamente.");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Integer id) {
		int res = service.deleteById(id);
		if (res != 0)
			return ResponseEntity.badRequest().body("No existe el ingrediente especificado.");
		return ResponseEntity.ok("Ingrediente eliminado correctamente.");
	}
}
