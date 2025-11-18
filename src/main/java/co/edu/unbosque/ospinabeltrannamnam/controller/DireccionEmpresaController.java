package co.edu.unbosque.ospinabeltrannamnam.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unbosque.ospinabeltrannamnam.dto.DireccionEmpresaDTO;
import co.edu.unbosque.ospinabeltrannamnam.service.DireccionEmpresaService;

@RestController
@RequestMapping("/api/direcciones")
@CrossOrigin(origins = "*")
public class DireccionEmpresaController {

	@Autowired
	private DireccionEmpresaService service;

	@PostMapping
	public ResponseEntity<String> crear(@RequestBody DireccionEmpresaDTO dto) {
		int res = service.create(dto);
		if (res != 0) {
			return ResponseEntity.badRequest().body("No se pudo crear la dirección.");
		}
		return ResponseEntity.ok("Dirección creada correctamente.");
	}

	@GetMapping
	public ResponseEntity<List<DireccionEmpresaDTO>> listar() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/empresa/{nit}")
	public ResponseEntity<List<DireccionEmpresaDTO>> listarPorEmpresa(@PathVariable Integer nit) {
		return ResponseEntity.ok(service.getByEmpresa(nit));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtener(@PathVariable Integer id) {
		DireccionEmpresaDTO dto = service.getById(id);
		if (dto == null)
			return ResponseEntity.badRequest().body("Dirección no encontrada.");
		return ResponseEntity.ok(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody DireccionEmpresaDTO dto) {
		int res = service.updateById(id, dto);
		if (res != 0)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok("Dirección actualizada correctamente.");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Integer id) {
		int res = service.deleteById(id);
		if (res != 0)
			return ResponseEntity.badRequest().body("No existe la dirección especificada.");
		return ResponseEntity.ok("Dirección eliminada correctamente.");
	}
}
