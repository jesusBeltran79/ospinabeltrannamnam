package co.edu.unbosque.ospinabeltrannamnam.service;

import co.edu.unbosque.ospinabeltrannamnam.dto.PlatoDTO;
import co.edu.unbosque.ospinabeltrannamnam.model.Plato;
import co.edu.unbosque.ospinabeltrannamnam.repository.PlatoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlatoService {

    @Autowired
    private PlatoRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    public int create(PlatoDTO data) {
        Plato p = modelMapper.map(data, Plato.class);
        p.setImagen(data.getImagen());
        repo.save(p);
        return 0;
    }

    public List<PlatoDTO> getAll() {
        return repo.findAll().stream().map(p -> {
            PlatoDTO dto = modelMapper.map(p, PlatoDTO.class);
            dto.setImagen(p.getImagen());
            return dto;
        }).collect(Collectors.toList());
    }

    public PlatoDTO getById(Integer id) {
        return repo.findById(id).map(p -> {
            PlatoDTO dto = modelMapper.map(p, PlatoDTO.class);
            dto.setImagen(p.getImagen());
            return dto;
        }).orElse(null);
    }

    public int updateById(Integer id, PlatoDTO newData) {
        Optional<Plato> opt = repo.findById(id);
        if (opt.isEmpty()) return 1;
        Plato p = opt.get();
        if (newData.getNombre() != null) p.setNombre(newData.getNombre());
        if (newData.getPrecioVenta() != null) p.setPrecioVenta(newData.getPrecioVenta());
        if (newData.getMargenGanancia() != null) p.setMargenGanancia(newData.getMargenGanancia());
        if (newData.getCosto() != null) p.setCosto(newData.getCosto());
        if (newData.getImagen() != null) p.setImagen(newData.getImagen());
        repo.save(p);
        return 0;
    }

    public int deleteById(Integer id) {
        return repo.findById(id).map(p -> { repo.delete(p); return 0; }).orElse(1);
    }

    public boolean exist(Integer id) { return repo.existsById(id); }
    public long count() { return repo.count(); }
}
