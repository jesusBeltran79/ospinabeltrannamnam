package co.edu.unbosque.ospinabeltrannamnam.service;

import co.edu.unbosque.ospinabeltrannamnam.dto.RecetaDTO;
import co.edu.unbosque.ospinabeltrannamnam.model.Receta;
import co.edu.unbosque.ospinabeltrannamnam.model.Ingrediente;
import co.edu.unbosque.ospinabeltrannamnam.repository.RecetaRepository;
import co.edu.unbosque.ospinabeltrannamnam.repository.IngredienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecetaService {

	@Autowired
	private RecetaRepository repo;

	@Autowired
	private IngredienteRepository ingredienteRepo;

	@Autowired
	private ModelMapper modelMapper;

	public int create(RecetaDTO data) {
		Receta r = new Receta();
		r.setNombre(data.getNombre());
		r.setDescripcion(data.getDescripcion());
		r.setTiempoPreparacion(data.getTiempoPreparacion() != null ? data.getTiempoPreparacion() : 0);
		r.setImagen(data.getImagen());
		if (data.getIngredientes() != null) {
			List<Ingrediente> list = data.getIngredientes().stream()
					.map(id -> ingredienteRepo.findById(id).orElse(null)).filter(Objects::nonNull)
					.collect(Collectors.toList());
			r.setIngredientes(list);
		}
		repo.save(r);
		return 0;
	}

	public List<RecetaDTO> getAll() {
		return repo.findAll().stream().map(r -> {
			RecetaDTO dto = modelMapper.map(r, RecetaDTO.class);
			dto.setIngredientes(r.getIngredientes().stream().map(Ingrediente::getCodigo).collect(Collectors.toList()));
			dto.setImagen(r.getImagen());
			return dto;
		}).collect(Collectors.toList());
	}

	public RecetaDTO getById(Integer id) {
		return repo.findById(id).map(r -> {
			RecetaDTO dto = modelMapper.map(r, RecetaDTO.class);
			dto.setIngredientes(r.getIngredientes().stream().map(Ingrediente::getCodigo).collect(Collectors.toList()));
			dto.setImagen(r.getImagen());
			return dto;
		}).orElse(null);
	}

	public int updateById(Integer id, RecetaDTO newData) {
		Optional<Receta> opt = repo.findById(id);
		if (opt.isEmpty())
			return 1;
		Receta r = opt.get();
		if (newData.getNombre() != null)
			r.setNombre(newData.getNombre());
		if (newData.getDescripcion() != null)
			r.setDescripcion(newData.getDescripcion());
		if (newData.getTiempoPreparacion() != null)
			r.setTiempoPreparacion(newData.getTiempoPreparacion());
		if (newData.getImagen() != null)
			r.setImagen(newData.getImagen());
		if (newData.getIngredientes() != null) {
			List<Ingrediente> list = newData.getIngredientes().stream()
					.map(i -> ingredienteRepo.findById(i).orElse(null)).filter(Objects::nonNull)
					.collect(Collectors.toList());
			r.setIngredientes(list);
		}
		repo.save(r);
		return 0;
	}

	public int deleteById(Integer id) {
		return repo.findById(id).map(r -> {
			repo.delete(r);
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
