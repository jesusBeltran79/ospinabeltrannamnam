package co.edu.unbosque.ospinabeltrannamnam.service;

import co.edu.unbosque.ospinabeltrannamnam.dto.InventarioDTO;
import co.edu.unbosque.ospinabeltrannamnam.model.Inventario;
import co.edu.unbosque.ospinabeltrannamnam.model.Administrador;
import co.edu.unbosque.ospinabeltrannamnam.repository.InventarioRepository;
import co.edu.unbosque.ospinabeltrannamnam.repository.AdministradorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository repo;

    @Autowired
    private AdministradorRepository adminRepo;

    @Autowired
    private ModelMapper modelMapper;

    public int create(InventarioDTO data) {
        Inventario inv = modelMapper.map(data, Inventario.class);
        if (data.getAdministradorId() != null) {
            Administrador a = adminRepo.findById(data.getAdministradorId()).orElse(null);
            inv.setAdministrador(a);
        }
        repo.save(inv);
        return 0;
    }

    public List<InventarioDTO> getAll() {
        return repo.findAll().stream().map(inv -> {
            InventarioDTO dto = modelMapper.map(inv, InventarioDTO.class);
            dto.setAdministradorId(inv.getAdministrador() != null ? inv.getAdministrador().getAdministradorId() : null);
            return dto;
        }).collect(Collectors.toList());
    }

    public InventarioDTO getById(Integer id) {
        return repo.findById(id).map(inv -> {
            InventarioDTO dto = modelMapper.map(inv, InventarioDTO.class);
            dto.setAdministradorId(inv.getAdministrador() != null ? inv.getAdministrador().getAdministradorId() : null);
            return dto;
        }).orElse(null);
    }

    public int updateById(Integer id, InventarioDTO newData) {
        Optional<Inventario> opt = repo.findById(id);
        if (opt.isEmpty()) return 1;
        Inventario inv = opt.get();
        if (newData.getCantidad() != null) inv.setCantidad(newData.getCantidad());
        if (newData.getAdministradorId() != null) {
            Administrador a = adminRepo.findById(newData.getAdministradorId()).orElse(null);
            inv.setAdministrador(a);
        }
        repo.save(inv);
        return 0;
    }

    public int deleteById(Integer id) {
        return repo.findById(id).map(i -> { repo.delete(i); return 0; }).orElse(1);
    }

    public boolean exist(Integer id) { return repo.existsById(id); }
    public long count() { return repo.count(); }
}
