package co.edu.unbosque.ospinabeltrannamnam.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unbosque.ospinabeltrannamnam.dto.AdministradorDTO;
import co.edu.unbosque.ospinabeltrannamnam.service.AdministradorService;

@RestController
@RequestMapping("/api/administradores")
@CrossOrigin(origins = "*")
public class AdministradorController {

	@Autowired
	private AdministradorService service;

	@PostMapping
	public ResponseEntity<String> crear(@RequestBody AdministradorDTO dto) {
		int res = service.create(dto);
		if (res != 0)
			return ResponseEntity.badRequest().body("No se pudo crear el administrador.");
		return ResponseEntity.ok("Administrador creado correctamente.");
	}

	@GetMapping
	public ResponseEntity<List<AdministradorDTO>> listar() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtener(@PathVariable Integer id) {
		AdministradorDTO dto = service.getById(id);
		if (dto == null)
			return ResponseEntity.badRequest().body("Administrador no encontrado.");
		return ResponseEntity.ok(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody AdministradorDTO dto) {
		int res = service.updateById(id, dto);
		if (res != 0)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok("Administrador actualizado correctamente.");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Integer id) {
		int res = service.deleteById(id);
		if (res != 0)
			return ResponseEntity.badRequest().body("No existe el administrador especificado.");
		return ResponseEntity.ok("Administrador eliminado correctamente.");
	}
}
