package co.edu.unbosque.ospinabeltrannamnam.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unbosque.ospinabeltrannamnam.dto.ProveedorDTO;
import co.edu.unbosque.ospinabeltrannamnam.service.ProveedorService;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*")
public class ProveedorController {

	@Autowired
	private ProveedorService service;

	@PostMapping
	public ResponseEntity<String> crear(@RequestBody ProveedorDTO dto) {
		int res = service.create(dto);
		if (res != 0)
			return ResponseEntity.badRequest().body("No se pudo crear el proveedor.");
		return ResponseEntity.ok("Proveedor creado correctamente.");
	}

	@GetMapping
	public ResponseEntity<List<ProveedorDTO>> listar() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtener(@PathVariable Integer id) {
		ProveedorDTO dto = service.getById(id);
		if (dto == null)
			return ResponseEntity.badRequest().body("Proveedor no encontrado.");
		return ResponseEntity.ok(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody ProveedorDTO dto) {
		int res = service.updateById(id, dto);
		if (res != 0)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok("Proveedor actualizado correctamente.");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Integer id) {
		int res = service.deleteById(id);
		if (res != 0)
			return ResponseEntity.badRequest().body("No existe el proveedor especificado.");
		return ResponseEntity.ok("Proveedor eliminado correctamente.");
	}
}
