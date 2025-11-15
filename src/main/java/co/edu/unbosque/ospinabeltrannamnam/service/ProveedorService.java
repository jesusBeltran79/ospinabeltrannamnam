package co.edu.unbosque.ospinabeltrannamnam.service;

import co.edu.unbosque.ospinabeltrannamnam.dto.ProveedorDTO;
import co.edu.unbosque.ospinabeltrannamnam.model.Proveedor;
import co.edu.unbosque.ospinabeltrannamnam.model.Empresa;
import co.edu.unbosque.ospinabeltrannamnam.repository.ProveedorRepository;
import co.edu.unbosque.ospinabeltrannamnam.repository.EmpresaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProveedorService {

	@Autowired
	private ProveedorRepository repo;

	@Autowired
	private EmpresaRepository empresaRepo;

	@Autowired
	private ModelMapper modelMapper;

	public int create(ProveedorDTO data) {
		Proveedor p = modelMapper.map(data, Proveedor.class);
		if (data.getNitEmpresa() != null) {
			Empresa e = empresaRepo.findById(data.getNitEmpresa()).orElse(null);
			p.setEmpresa(e);
		}
		repo.save(p);
		return 0;
	}

	public List<ProveedorDTO> getAll() {
		return repo.findAll().stream().map(p -> {
			ProveedorDTO dto = modelMapper.map(p, ProveedorDTO.class);
			dto.setNitEmpresa(p.getEmpresa() != null ? p.getEmpresa().getNit() : null);
			return dto;
		}).collect(Collectors.toList());
	}

	public ProveedorDTO getById(Integer id) {
		return repo.findById(id).map(p -> {
			ProveedorDTO dto = modelMapper.map(p, ProveedorDTO.class);
			dto.setNitEmpresa(p.getEmpresa() != null ? p.getEmpresa().getNit() : null);
			return dto;
		}).orElse(null);
	}

	public int updateById(Integer id, ProveedorDTO newData) {
		Optional<Proveedor> opt = repo.findById(id);
		if (opt.isEmpty())
			return 1;
		Proveedor p = opt.get();
		if (newData.getPrimerNombre() != null)
			p.setPrimerNombre(newData.getPrimerNombre());
		if (newData.getSegundoNombre() != null)
			p.setSegundoNombre(newData.getSegundoNombre());
		if (newData.getPrimerApellido() != null)
			p.setPrimerApellido(newData.getPrimerApellido());
		if (newData.getSegundoApellido() != null)
			p.setSegundoApellido(newData.getSegundoApellido());
		if (newData.getEmail() != null)
			p.setEmail(newData.getEmail());
		if (newData.getTelefono() != null)
			p.setTelefono(newData.getTelefono());
		if (newData.getCargo() != null)
			p.setCargo(newData.getCargo());
		if (newData.getNitEmpresa() != null) {
			Empresa e = empresaRepo.findById(newData.getNitEmpresa()).orElse(null);
			p.setEmpresa(e);
		}
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
