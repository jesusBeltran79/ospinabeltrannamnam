package co.edu.unbosque.ospinabeltrannamnam.service;

import co.edu.unbosque.ospinabeltrannamnam.dto.DireccionEmpresaDTO; 
import co.edu.unbosque.ospinabeltrannamnam.model.DireccionEmpresa;
import co.edu.unbosque.ospinabeltrannamnam.model.Empresa;
import co.edu.unbosque.ospinabeltrannamnam.repository.DireccionEmpresaRepository;
import co.edu.unbosque.ospinabeltrannamnam.repository.EmpresaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DireccionEmpresaService {

	@Autowired
	private DireccionEmpresaRepository repo;

	@Autowired
	private EmpresaRepository empresaRepo;

	@Autowired
	private ModelMapper modelMapper;

	public int create(DireccionEmpresaDTO data) {

		DireccionEmpresa d = new DireccionEmpresa();
		d.setDireccionText(data.getDireccionText());
		d.setCiudad(data.getCiudad());
		d.setDepartamento(data.getDepartamento());
		d.setPais(data.getPais());
		d = repo.save(d);

		if (data.getEmpresaNit() != null) {
			Optional<Empresa> opt = empresaRepo.findById(data.getEmpresaNit());
			if (opt.isPresent()) {
				Empresa e = opt.get();
				d.setEmpresa(e);
				repo.save(d);
			}
		}
		return 0;
	}

	public List<DireccionEmpresaDTO> getAll() {
		return repo.findAll().stream().map(e -> modelMapper.map(e, DireccionEmpresaDTO.class))
				.collect(Collectors.toList());
	}

	public DireccionEmpresaDTO getById(Integer id) {
		return repo.findById(id).map(e -> {
			DireccionEmpresaDTO dto = modelMapper.map(e, DireccionEmpresaDTO.class);
			if (e.getEmpresa() != null)
				dto.setEmpresaNit(e.getEmpresa().getNit());
			return dto;
		}).orElse(null);
	}

	public int updateById(Integer id, DireccionEmpresaDTO newData) {
		Optional<DireccionEmpresa> opt = repo.findById(id);
		if (opt.isEmpty())
			return 1;
		DireccionEmpresa d = opt.get();
		if (newData.getDireccionText() != null)
			d.setDireccionText(newData.getDireccionText());
		if (newData.getCiudad() != null)
			d.setCiudad(newData.getCiudad());
		if (newData.getDepartamento() != null)
			d.setDepartamento(newData.getDepartamento());
		if (newData.getPais() != null)
			d.setPais(newData.getPais());
		repo.save(d);

		if (newData.getEmpresaNit() != null) {
			Optional<Empresa> optE = empresaRepo.findById(newData.getEmpresaNit());
			if (optE.isPresent()) {
				d.setEmpresa(optE.get());
				repo.save(d);
			}
		}

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

	public List<DireccionEmpresaDTO> getByEmpresa(Integer nit) {
		List<DireccionEmpresa> list = repo.findByEmpresaNit(nit);
		if (list == null || list.isEmpty())
			return Collections.emptyList();
		return list.stream().map(d -> {
			DireccionEmpresaDTO dto = modelMapper.map(d, DireccionEmpresaDTO.class);
			if (d.getEmpresa() != null)
				dto.setEmpresaNit(d.getEmpresa().getNit());
			return dto;
		}).collect(Collectors.toList());
	}
}
