package co.edu.unbosque.ospinabeltrannamnam.service;

import co.edu.unbosque.ospinabeltrannamnam.dto.IngredienteDTO;
import co.edu.unbosque.ospinabeltrannamnam.model.Ingrediente;
import co.edu.unbosque.ospinabeltrannamnam.repository.IngredienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IngredienteService {

	@Autowired
	private IngredienteRepository repo;

	@Autowired
	private ModelMapper modelMapper;

	public int create(IngredienteDTO data) {
		Ingrediente i = modelMapper.map(data, Ingrediente.class);
		if (i.getEstado() == null)
			i.setEstado("disponible");
		i.setCodigo(null);

		if (i.getCostoUnitario() == null)
			i.setCostoUnitario(0);
		repo.save(i);
		return 0;
	}

	public List<IngredienteDTO> getAll() {
		return repo.findAll().stream().map(i -> modelMapper.map(i, IngredienteDTO.class)).collect(Collectors.toList());
	}

	public IngredienteDTO getById(Integer id) {
		return repo.findById(id).map(i -> modelMapper.map(i, IngredienteDTO.class)).orElse(null);
	}

	public int updateById(Integer id, IngredienteDTO newData) {
		Optional<Ingrediente> opt = repo.findById(id);
		if (opt.isEmpty())
			return 1;
		Ingrediente i = opt.get();
		if (newData.getNombre() != null)
			i.setNombre(newData.getNombre());
		if (newData.getCantidad() != null)
			i.setCantidad(newData.getCantidad());
		if (newData.getEstado() != null)
			i.setEstado(newData.getEstado());
		if (newData.getCostoUnitario() != null)
			i.setCostoUnitario(newData.getCostoUnitario());
		repo.save(i);
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
