package co.edu.unbosque.ospinabeltrannamnam.service;

import co.edu.unbosque.ospinabeltrannamnam.dto.AdministradorDTO;
import co.edu.unbosque.ospinabeltrannamnam.model.Administrador;
import co.edu.unbosque.ospinabeltrannamnam.repository.AdministradorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdministradorService {

	@Autowired
	private AdministradorRepository repo;

	@Autowired
	private ModelMapper modelMapper;

	public int create(AdministradorDTO data) {
		Administrador a = modelMapper.map(data, Administrador.class);
		repo.save(a);
		return 0;
	}

	public List<AdministradorDTO> getAll() {
		return repo.findAll().stream().map(a -> modelMapper.map(a, AdministradorDTO.class))
				.collect(Collectors.toList());
	}

	public AdministradorDTO getById(Integer id) {
		return repo.findById(id).map(a -> modelMapper.map(a, AdministradorDTO.class)).orElse(null);
	}

	public int updateById(Integer id, AdministradorDTO newData) {
		Optional<Administrador> opt = repo.findById(id);
		if (opt.isEmpty())
			return 1;
		Administrador a = opt.get();
		if (newData.getNombreAdministrador() != null)
			a.setNombreAdministrador(newData.getNombreAdministrador());
		if (newData.getClave() != null)
			a.setClave(newData.getClave());
		repo.save(a);
		return 0;
	}

	public int deleteById(Integer id) {
		return repo.findById(id).map(a -> {
			repo.delete(a);
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
