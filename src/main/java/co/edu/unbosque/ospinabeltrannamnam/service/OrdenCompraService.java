package co.edu.unbosque.ospinabeltrannamnam.service;

import co.edu.unbosque.ospinabeltrannamnam.dto.IngredienteDTO;
import co.edu.unbosque.ospinabeltrannamnam.dto.OrdenCompraDTO;
import co.edu.unbosque.ospinabeltrannamnam.model.Administrador;
import co.edu.unbosque.ospinabeltrannamnam.model.Empresa;
import co.edu.unbosque.ospinabeltrannamnam.model.Ingrediente;
import co.edu.unbosque.ospinabeltrannamnam.model.OrdenCompra;
import co.edu.unbosque.ospinabeltrannamnam.repository.AdministradorRepository;
import co.edu.unbosque.ospinabeltrannamnam.repository.EmpresaRepository;
import co.edu.unbosque.ospinabeltrannamnam.repository.IngredienteRepository;
import co.edu.unbosque.ospinabeltrannamnam.repository.OrdenCompraRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrdenCompraService {

    @Autowired
    private OrdenCompraRepository repo;

    @Autowired
    private AdministradorRepository adminRepo;

    @Autowired
    private IngredienteRepository ingredienteRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    @Autowired
    private ModelMapper modelMapper;

    public int create(OrdenCompraDTO data) {
        OrdenCompra o = new OrdenCompra();

        // fechaEmision
        if (data.getFechaEmision() != null) {
            o.setFechaEmision(data.getFechaEmision());
        } else {
            o.setFechaEmision(LocalDate.now());
        }

        if (data.getEstado() != null) o.setEstado(data.getEstado());

        // administrador
        if (data.getAdministradorId() != null) {
            Administrador a = adminRepo.findById(data.getAdministradorId()).orElse(null);
            o.setAdministrador(a);
        } else {
            o.setAdministrador(null);
        }

        // empresa (nit)
        if (data.getNitEmpresa() != null) {
            Empresa e = empresaRepo.findById(data.getNitEmpresa()).orElse(null);
            o.setEmpresa(e);
        } else {
            o.setEmpresa(null);
        }

        // ingredientes
        if (data.getIngredientes() != null) {
            List<Ingrediente> ingList = new ArrayList<>();
            for (IngredienteDTO ingDto : data.getIngredientes()) {
                if (ingDto == null) continue;
                if (ingDto.getCodigo() != null) {
                    Optional<Ingrediente> opt = ingredienteRepo.findById(ingDto.getCodigo());
                    if (opt.isPresent()) {
                        Ingrediente ing = opt.get();
                        // actualizar cantidad solicitada y costoUnitario si se envía
                        ing.setCantidad(ingDto.getCantidad());
                        if (ingDto.getCostoUnitario() != null) ing.setCostoUnitario(ingDto.getCostoUnitario());
                        ingredienteRepo.save(ing);
                        ingList.add(ing);
                    } else {
                        Ingrediente nuevo = new Ingrediente();
                        nuevo.setNombre(ingDto.getNombre());
                        nuevo.setCantidad(ingDto.getCantidad());
                        nuevo.setEstado(ingDto.getEstado() != null ? ingDto.getEstado() : "disponible");
                        nuevo.setCostoUnitario(ingDto.getCostoUnitario());
                        ingredienteRepo.save(nuevo);
                        ingList.add(nuevo);
                    }
                } else {
                    Ingrediente nuevo = new Ingrediente();
                    nuevo.setNombre(ingDto.getNombre());
                    nuevo.setCantidad(ingDto.getCantidad());
                    nuevo.setEstado(ingDto.getEstado() != null ? ingDto.getEstado() : "disponible");
                    nuevo.setCostoUnitario(ingDto.getCostoUnitario());
                    ingredienteRepo.save(nuevo);
                    ingList.add(nuevo);
                }
            }
            o.setIngredientes(ingList);
        }

        repo.save(o);
        return 0;
    }

    public List<OrdenCompraDTO> getAll() {
        return repo.findAll().stream().map(o -> {
            OrdenCompraDTO dto = new OrdenCompraDTO();
            dto.setOrdenId(o.getOrdenId());
            dto.setFechaEmision(o.getFechaEmision());
            dto.setEstado(o.getEstado());
            dto.setAdministradorId(o.getAdministrador() != null ? o.getAdministrador().getAdministradorId() : null);
            dto.setAdministradorNombre(o.getAdministrador() != null ? o.getAdministrador().getNombreAdministrador() : null);
            dto.setNitEmpresa(o.getEmpresa() != null ? o.getEmpresa().getNit() : null);

            if (o.getIngredientes() != null) {
                List<IngredienteDTO> ingDtos = o.getIngredientes().stream()
                        .map(i -> {
                            IngredienteDTO idto = modelMapper.map(i, IngredienteDTO.class);
                            // aseguramos que el DTO tenga costoUnitario y cantidad
                            idto.setCostoUnitario(i.getCostoUnitario());
                            idto.setCantidad(i.getCantidad());
                            return idto;
                        })
                        .collect(Collectors.toList());
                dto.setIngredientes(ingDtos);
            } else {
                dto.setIngredientes(new ArrayList<>());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    public OrdenCompraDTO getById(Integer id) {
        return repo.findById(id).map(o -> {
            OrdenCompraDTO dto = new OrdenCompraDTO();
            dto.setOrdenId(o.getOrdenId());
            dto.setFechaEmision(o.getFechaEmision());
            dto.setEstado(o.getEstado());
            dto.setAdministradorId(o.getAdministrador() != null ? o.getAdministrador().getAdministradorId() : null);
            dto.setAdministradorNombre(o.getAdministrador() != null ? o.getAdministrador().getNombreAdministrador() : null);
            dto.setNitEmpresa(o.getEmpresa() != null ? o.getEmpresa().getNit() : null);

            if (o.getIngredientes() != null) {
                List<IngredienteDTO> ingDtos = o.getIngredientes().stream()
                        .map(i -> {
                            IngredienteDTO idto = modelMapper.map(i, IngredienteDTO.class);
                            idto.setCostoUnitario(i.getCostoUnitario());
                            idto.setCantidad(i.getCantidad());
                            return idto;
                        })
                        .collect(Collectors.toList());
                dto.setIngredientes(ingDtos);
            } else {
                dto.setIngredientes(new ArrayList<>());
            }
            return dto;
        }).orElse(null);
    }

    public int updateById(Integer id, OrdenCompraDTO newData) {
        Optional<OrdenCompra> opt = repo.findById(id);
        if (opt.isEmpty()) return 1;
        OrdenCompra o = opt.get();

        if (newData.getFechaEmision() != null) o.setFechaEmision(newData.getFechaEmision());
        if (newData.getEstado() != null) o.setEstado(newData.getEstado());

        if (newData.getAdministradorId() != null) {
            Administrador a = adminRepo.findById(newData.getAdministradorId()).orElse(null);
            o.setAdministrador(a);
        } else {
            o.setAdministrador(null);
        }

        if (newData.getNitEmpresa() != null) {
            Empresa e = empresaRepo.findById(newData.getNitEmpresa()).orElse(null);
            o.setEmpresa(e);
        } else {
            o.setEmpresa(null);
        }

        if (newData.getIngredientes() != null) {
            List<Ingrediente> ingList = new ArrayList<>();
            for (IngredienteDTO ingDto : newData.getIngredientes()) {
                if (ingDto == null) continue;
                if (ingDto.getCodigo() != null) {
                    Optional<Ingrediente> optIng = ingredienteRepo.findById(ingDto.getCodigo());
                    if (optIng.isPresent()) {
                        Ingrediente ing = optIng.get();
                        ing.setCantidad(ingDto.getCantidad());
                        if (ingDto.getCostoUnitario() != null) ing.setCostoUnitario(ingDto.getCostoUnitario());
                        ingredienteRepo.save(ing);
                        ingList.add(ing);
                    } else {
                        Ingrediente nuevo = new Ingrediente();
                        nuevo.setNombre(ingDto.getNombre());
                        nuevo.setCantidad(ingDto.getCantidad());
                        nuevo.setEstado(ingDto.getEstado() != null ? ingDto.getEstado() : "disponible");
                        nuevo.setCostoUnitario(ingDto.getCostoUnitario());
                        ingredienteRepo.save(nuevo);
                        ingList.add(nuevo);
                    }
                } else {
                    Ingrediente nuevo = new Ingrediente();
                    nuevo.setNombre(ingDto.getNombre());
                    nuevo.setCantidad(ingDto.getCantidad());
                    nuevo.setEstado(ingDto.getEstado() != null ? ingDto.getEstado() : "disponible");
                    nuevo.setCostoUnitario(ingDto.getCostoUnitario());
                    ingredienteRepo.save(nuevo);
                    ingList.add(nuevo);
                }
            }
            o.setIngredientes(ingList);
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
