package co.edu.unbosque.ospinabeltrannamnam.service;

import co.edu.unbosque.ospinabeltrannamnam.dto.EmpresaDTO;
import co.edu.unbosque.ospinabeltrannamnam.model.Empresa;
import co.edu.unbosque.ospinabeltrannamnam.model.DireccionEmpresa;
import co.edu.unbosque.ospinabeltrannamnam.repository.EmpresaRepository;
import co.edu.unbosque.ospinabeltrannamnam.repository.DireccionEmpresaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository repo;

	@Autowired
	private DireccionEmpresaRepository direccionRepo;

	@Autowired
	private ModelMapper modelMapper;

	public int create(EmpresaDTO data) {
		Empresa e = modelMapper.map(data, Empresa.class);
		if (data.getDireccionId() != null) {
			DireccionEmpresa d = direccionRepo.findById(data.getDireccionId()).orElse(null);
			e.setDireccion(d);
		}
		repo.save(e);
		return 0;
	}

	public List<EmpresaDTO> getAll() {
		return repo.findAll().stream().map(e -> {
			EmpresaDTO dto = modelMapper.map(e, EmpresaDTO.class);
			dto.setDireccionId(e.getDireccion() != null ? e.getDireccion().getDireccionId() : null);
			return dto;
		}).collect(Collectors.toList());
	}

	public EmpresaDTO getById(Integer id) {
		return repo.findById(id).map(e -> {
			EmpresaDTO dto = modelMapper.map(e, EmpresaDTO.class);
			dto.setDireccionId(e.getDireccion() != null ? e.getDireccion().getDireccionId() : null);
			return dto;
		}).orElse(null);
	}

	public int updateById(Integer id, EmpresaDTO newData) {
		Optional<Empresa> opt = repo.findById(id);
		if (opt.isEmpty())
			return 1;
		Empresa e = opt.get();
		if (newData.getNombre() != null)
			e.setNombre(newData.getNombre());
		if (newData.getEmail() != null)
			e.setEmail(newData.getEmail());
		if (newData.getTelefono() != null)
			e.setTelefono(newData.getTelefono());
		if (newData.getEstado() != null)
			e.setEstado(newData.getEstado());
		if (newData.getDireccionId() != null) {
			DireccionEmpresa d = direccionRepo.findById(newData.getDireccionId()).orElse(null);
			e.setDireccion(d);
		}
		repo.save(e);
		return 0;
	}

	public int deleteById(Integer id) {
		return repo.findById(id).map(e -> {
			repo.delete(e);
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
