package co.edu.unbosque.ospinabeltrannamnam.service;

import co.edu.unbosque.ospinabeltrannamnam.dto.OrdenCompraDTO;
import co.edu.unbosque.ospinabeltrannamnam.model.OrdenCompra;
import co.edu.unbosque.ospinabeltrannamnam.model.Empresa;
import co.edu.unbosque.ospinabeltrannamnam.model.Administrador;
import co.edu.unbosque.ospinabeltrannamnam.repository.OrdenCompraRepository;
import co.edu.unbosque.ospinabeltrannamnam.repository.EmpresaRepository;
import co.edu.unbosque.ospinabeltrannamnam.repository.AdministradorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrdenCompraService {

    @Autowired
    private OrdenCompraRepository repo;

    @Autowired
    private EmpresaRepository empresaRepo;

    @Autowired
    private AdministradorRepository adminRepo;

    @Autowired
    private ModelMapper modelMapper;

    public int create(OrdenCompraDTO data) {
        OrdenCompra o = new OrdenCompra();
        o.setFechaEmision(data.getFechaEmision());
        o.setFechaRecepcion(data.getFechaRecepcion());
        o.setEstado(data.getEstado());
        o.setTotal(data.getTotal());
        if (data.getNitEmpresa() != null) {
            Empresa e = empresaRepo.findById(data.getNitEmpresa()).orElse(null);
            o.setEmpresa(e);
        }
        if (data.getAdministradorId() != null) {
            Administrador a = adminRepo.findById(data.getAdministradorId()).orElse(null);
            o.setAdministrador(a);
        }
        repo.save(o);
        return 0;
    }

    public List<OrdenCompraDTO> getAll() {
        return repo.findAll().stream().map(o -> {
            OrdenCompraDTO dto = modelMapper.map(o, OrdenCompraDTO.class);
            dto.setNitEmpresa(o.getEmpresa() != null ? o.getEmpresa().getNit() : null);
            dto.setAdministradorId(o.getAdministrador() != null ? o.getAdministrador().getAdministradorId() : null);
            return dto;
        }).collect(Collectors.toList());
    }

    public OrdenCompraDTO getById(Integer id) {
        return repo.findById(id).map(o -> {
            OrdenCompraDTO dto = modelMapper.map(o, OrdenCompraDTO.class);
            dto.setNitEmpresa(o.getEmpresa() != null ? o.getEmpresa().getNit() : null);
            dto.setAdministradorId(o.getAdministrador() != null ? o.getAdministrador().getAdministradorId() : null);
            return dto;
        }).orElse(null);
    }

    public int updateById(Integer id, OrdenCompraDTO newData) {
        Optional<OrdenCompra> opt = repo.findById(id);
        if (opt.isEmpty()) return 1;
        OrdenCompra o = opt.get();
        if (newData.getFechaEmision() != null) o.setFechaEmision(newData.getFechaEmision());
        if (newData.getFechaRecepcion() != null) o.setFechaRecepcion(newData.getFechaRecepcion());
        if (newData.getEstado() != null) o.setEstado(newData.getEstado());
        if (newData.getTotal() != null) o.setTotal(newData.getTotal());
        if (newData.getNitEmpresa() != null) {
            Empresa e = empresaRepo.findById(newData.getNitEmpresa()).orElse(null);
            o.setEmpresa(e);
        }
        if (newData.getAdministradorId() != null) {
            Administrador a = adminRepo.findById(newData.getAdministradorId()).orElse(null);
            o.setAdministrador(a);
        }
        repo.save(o);
        return 0;
    }

    public int deleteById(Integer id) {
        return repo.findById(id).map(o -> { repo.delete(o); return 0; }).orElse(1);
    }

    public boolean exist(Integer id) { return repo.existsById(id); }
    public long count() { return repo.count(); }
}
