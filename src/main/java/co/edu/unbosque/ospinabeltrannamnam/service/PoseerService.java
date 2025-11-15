package co.edu.unbosque.ospinabeltrannamnam.service;

import co.edu.unbosque.ospinabeltrannamnam.dto.PoseerDTO;
import co.edu.unbosque.ospinabeltrannamnam.model.Poseer;
import co.edu.unbosque.ospinabeltrannamnam.model.Ingrediente;
import co.edu.unbosque.ospinabeltrannamnam.model.Inventario;
import co.edu.unbosque.ospinabeltrannamnam.repository.PoseerRepository;
import co.edu.unbosque.ospinabeltrannamnam.repository.IngredienteRepository;
import co.edu.unbosque.ospinabeltrannamnam.repository.InventarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PoseerService {

    @Autowired
    private PoseerRepository repo;

    @Autowired
    private IngredienteRepository ingredienteRepo;

    @Autowired
    private InventarioRepository inventarioRepo;

    @Autowired
    private ModelMapper modelMapper;

    public int create(PoseerDTO data) {
        Poseer p = new Poseer();
        if (data.getCodigoIngrediente() != null) {
            Ingrediente ing = ingredienteRepo.findById(data.getCodigoIngrediente()).orElse(null);
            p.setIngrediente(ing);
        }
        if (data.getInventarioId() != null) {
            Inventario inv = inventarioRepo.findById(data.getInventarioId()).orElse(null);
            p.setInventario(inv);
        }
        p.setStockMinimo(data.getStockMinimo());
        repo.save(p);
        return 0;
    }

    public List<PoseerDTO> getAll() {
        return repo.findAll().stream().map(p -> {
            PoseerDTO dto = modelMapper.map(p, PoseerDTO.class);
            dto.setCodigoIngrediente(p.getIngrediente() != null ? p.getIngrediente().getCodigo() : null);
            dto.setInventarioId(p.getInventario() != null ? p.getInventario().getInventarioId() : null);
            return dto;
        }).collect(Collectors.toList());
    }

    public PoseerDTO getById(Integer id) {
        return repo.findById(id).map(p -> {
            PoseerDTO dto = modelMapper.map(p, PoseerDTO.class);
            dto.setCodigoIngrediente(p.getIngrediente() != null ? p.getIngrediente().getCodigo() : null);
            dto.setInventarioId(p.getInventario() != null ? p.getInventario().getInventarioId() : null);
            return dto;
        }).orElse(null);
    }

    public int updateById(Integer id, PoseerDTO newData) {
        Optional<Poseer> opt = repo.findById(id);
        if (opt.isEmpty()) return 1;
        Poseer p = opt.get();
        if (newData.getCodigoIngrediente() != null) {
            Ingrediente ing = ingredienteRepo.findById(newData.getCodigoIngrediente()).orElse(null);
            p.setIngrediente(ing);
        }
        if (newData.getInventarioId() != null) {
            Inventario inv = inventarioRepo.findById(newData.getInventarioId()).orElse(null);
            p.setInventario(inv);
        }
        if (newData.getStockMinimo() != null) p.setStockMinimo(newData.getStockMinimo());
        repo.save(p);
        return 0;
    }

    public int deleteById(Integer id) {
        return repo.findById(id).map(p -> { repo.delete(p); return 0; }).orElse(1);
    }

    public boolean exist(Integer id) { return repo.existsById(id); }
    public long count() { return repo.count(); }
}
