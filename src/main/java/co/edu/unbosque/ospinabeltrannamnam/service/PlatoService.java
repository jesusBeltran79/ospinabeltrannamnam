package co.edu.unbosque.ospinabeltrannamnam.service;

import co.edu.unbosque.ospinabeltrannamnam.dto.PlatoDTO;
import co.edu.unbosque.ospinabeltrannamnam.model.Plato;
import co.edu.unbosque.ospinabeltrannamnam.model.Receta;
import co.edu.unbosque.ospinabeltrannamnam.model.Ingrediente;
import co.edu.unbosque.ospinabeltrannamnam.repository.PlatoRepository;
import co.edu.unbosque.ospinabeltrannamnam.repository.RecetaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlatoService {
	private static final double MARGEN = 0.35;

	@Autowired
	private PlatoRepository repo;

	@Autowired
	private RecetaRepository recetaRepo;

	@Autowired
	private ModelMapper modelMapper;

	public int create(PlatoDTO data) {
		if (data == null)
			return 1;
		Plato p = modelMapper.map(data, Plato.class);
		if (p.getMargenGanancia() == null)
			p.setMargenGanancia(35);
		p.setRecetaId(data.getRecetaId());
		repo.save(p);
		return 0;
	}

	public List<PlatoDTO> getAll() {
		return repo.findAll().stream().map(p -> {
			PlatoDTO dto = modelMapper.map(p, PlatoDTO.class);
			dto.setImagen(p.getImagen());
			Integer recetaId = p.getRecetaId();
			Integer costoCalc = null;

			if (recetaId != null) {
				Optional<Receta> optRec = recetaRepo.findById(recetaId);
				if (optRec.isPresent()) {
					Receta r = optRec.get();
					int sum = 0;
					if (r.getIngredientes() != null) {
						for (Ingrediente ing : r.getIngredientes()) {
							int cantidad = ing.getCantidad() != null ? ing.getCantidad() : 0;
							int costoUnit = ing.getCostoUnitario() != null ? ing.getCostoUnitario() : 0;
							sum += cantidad * costoUnit;
						}
					}
					costoCalc = sum;
				}
			}

			if (costoCalc == null) {
				costoCalc = p.getCosto() != null ? p.getCosto() : 0;
			}

			dto.setCosto(costoCalc);
			dto.setMargenGanancia(35);
			int precioVenta = (int) Math.round(costoCalc * (1.0 + MARGEN));
			dto.setPrecioVenta(precioVenta);
			dto.setRecetaId(p.getRecetaId());
			return dto;
		}).collect(Collectors.toList());
	}

	public PlatoDTO getById(Integer id) {
		return repo.findById(id).map(p -> {
			PlatoDTO dto = modelMapper.map(p, PlatoDTO.class);
			dto.setImagen(p.getImagen());
			dto.setRecetaId(p.getRecetaId());

			Integer recetaId = p.getRecetaId();
			Integer costoCalc = null;

			if (recetaId != null) {
				Optional<Receta> optRec = recetaRepo.findById(recetaId);
				if (optRec.isPresent()) {
					Receta r = optRec.get();
					int sum = 0;
					if (r.getIngredientes() != null) {
						for (Ingrediente ing : r.getIngredientes()) {
							int cantidad = ing.getCantidad() != null ? ing.getCantidad() : 0;
							int costoUnit = ing.getCostoUnitario() != null ? ing.getCostoUnitario() : 0;
							sum += cantidad * costoUnit;
						}
					}
					costoCalc = sum;
				}
			}

			if (costoCalc == null) {
				costoCalc = p.getCosto() != null ? p.getCosto() : 0;
			}

			dto.setCosto(costoCalc);
			dto.setMargenGanancia(35);
			int precioVenta = (int) Math.round(costoCalc * (1.0 + MARGEN));
			dto.setPrecioVenta(precioVenta);

			return dto;
		}).orElse(null);
	}

	public int updateById(Integer id, PlatoDTO newData) {
		Optional<Plato> opt = repo.findById(id);
		if (opt.isEmpty())
			return 1;
		Plato p = opt.get();
		if (newData.getNombre() != null)
			p.setNombre(newData.getNombre());
		if (newData.getPrecioVenta() != null)
			p.setPrecioVenta(newData.getPrecioVenta());
		if (newData.getMargenGanancia() != null)
			p.setMargenGanancia(newData.getMargenGanancia());
		if (newData.getCosto() != null)
			p.setCosto(newData.getCosto());
		if (newData.getImagen() != null)
			p.setImagen(newData.getImagen());
		if (newData.getRecetaId() != null)
			p.setRecetaId(newData.getRecetaId());
		repo.save(p);
		return 0;
	}

	public int deleteById(Integer id) {
		return repo.findById(id).map(p -> {
			repo.delete(p);
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
