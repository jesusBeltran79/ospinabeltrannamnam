package co.edu.unbosque.ospinabeltrannamnam.service;

import co.edu.unbosque.ospinabeltrannamnam.dto.InventarioDTO;
import co.edu.unbosque.ospinabeltrannamnam.model.Inventario;
import co.edu.unbosque.ospinabeltrannamnam.model.Administrador;
import co.edu.unbosque.ospinabeltrannamnam.model.Ingrediente;
import co.edu.unbosque.ospinabeltrannamnam.repository.InventarioRepository;
import co.edu.unbosque.ospinabeltrannamnam.repository.AdministradorRepository;
import co.edu.unbosque.ospinabeltrannamnam.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InventarioService {

	@Autowired
	private InventarioRepository repo;

	@Autowired
	private AdministradorRepository adminRepo;

	@Autowired
	private IngredienteRepository ingredienteRepo;

	@Transactional
	public Integer create(InventarioDTO data) {
		Inventario inv = new Inventario();
		inv.setCantidad(data.getCantidad());
		inv.setStockMinimo(data.getStockMinimo());

		if (data.getAdministradorId() != null) {
			Administrador a = adminRepo.findById(data.getAdministradorId())
					.orElseThrow(() -> new RuntimeException("Administrador no existe: " + data.getAdministradorId()));
			inv.setAdministrador(a);
		} else {
			inv.setAdministrador(null);
		}

		if (data.getIngredienteId() != null) {
			Ingrediente ing = ingredienteRepo.findById(data.getIngredienteId())
					.orElseThrow(() -> new RuntimeException("Ingrediente no existe: " + data.getIngredienteId()));
			inv.setIngrediente(ing);
		} else {
			inv.setIngrediente(null);
		}

		Inventario saved = repo.save(inv);
		return saved.getInventarioId();
	}

	public List<InventarioDTO> getAll() {
		return repo.findAll().stream().map(inv -> {
			InventarioDTO dto = new InventarioDTO();
			dto.setInventarioId(inv.getInventarioId());
			dto.setCantidad(inv.getCantidad());
			dto.setAdministradorId(inv.getAdministrador() != null ? inv.getAdministrador().getAdministradorId() : null);
			dto.setIngredienteId(inv.getIngrediente() != null ? inv.getIngrediente().getCodigo() : null);
			dto.setStockMinimo(inv.getStockMinimo());
			return dto;
		}).collect(Collectors.toList());
	}

	public InventarioDTO getById(Integer id) {
		return repo.findById(id).map(inv -> {
			InventarioDTO dto = new InventarioDTO();
			dto.setInventarioId(inv.getInventarioId());
			dto.setCantidad(inv.getCantidad());
			dto.setAdministradorId(inv.getAdministrador() != null ? inv.getAdministrador().getAdministradorId() : null);
			dto.setIngredienteId(inv.getIngrediente() != null ? inv.getIngrediente().getCodigo() : null);
			dto.setStockMinimo(inv.getStockMinimo());
			return dto;
		}).orElse(null);
	}

	@Transactional
	public int updateById(Integer id, InventarioDTO newData) {
		Optional<Inventario> opt = repo.findById(id);
		if (opt.isEmpty())
			return 1;
		Inventario inv = opt.get();

		if (newData.getCantidad() != null)
			inv.setCantidad(newData.getCantidad());
		if (newData.getStockMinimo() != null)
			inv.setStockMinimo(newData.getStockMinimo());

		if (newData.getAdministradorId() != null) {
			Administrador a = adminRepo.findById(newData.getAdministradorId()).orElseThrow(
					() -> new RuntimeException("Administrador no existe: " + newData.getAdministradorId()));
			inv.setAdministrador(a);
		} else {
			inv.setAdministrador(null);
		}

		if (newData.getIngredienteId() != null) {
			Ingrediente ing = ingredienteRepo.findById(newData.getIngredienteId())
					.orElseThrow(() -> new RuntimeException("Ingrediente no existe: " + newData.getIngredienteId()));
			inv.setIngrediente(ing);
		} else {
			inv.setIngrediente(null);
		}

		repo.save(inv);
		return 0;
	}

	public int deleteById(Integer id) {
		return repo.findById(id).map(i -> {
			repo.delete(i);
			return 0;
		}).orElse(1);
	}

	public boolean exist(Integer id) {
		return repo.existsById(id);
	}

	public long count() {
		return repo.count();
	}
}
