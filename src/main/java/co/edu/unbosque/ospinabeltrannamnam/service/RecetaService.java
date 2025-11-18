package co.edu.unbosque.ospinabeltrannamnam.service;

import co.edu.unbosque.ospinabeltrannamnam.dto.RecetaDTO;
import co.edu.unbosque.ospinabeltrannamnam.dto.IngredienteDTO;
import co.edu.unbosque.ospinabeltrannamnam.model.Receta;
import co.edu.unbosque.ospinabeltrannamnam.model.Ingrediente;
import co.edu.unbosque.ospinabeltrannamnam.repository.RecetaRepository;
import co.edu.unbosque.ospinabeltrannamnam.repository.IngredienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecetaService {

    @Autowired
    private RecetaRepository repo;

    @Autowired
    private IngredienteRepository ingredienteRepo;

    @Autowired
    private ModelMapper modelMapper;

    public int create(RecetaDTO data) {
        if (data == null) return 1;
        Receta r = new Receta();
        r.setNombre(data.getNombre());
        r.setDescripcion(data.getDescripcion());
        r.setTiempoPreparacion(data.getTiempoPreparacion() != null ? data.getTiempoPreparacion() : 0);
        r.setImagen(data.getImagen());

        if (data.getIngredientes() != null) {
            List<Ingrediente> ingList = new ArrayList<>();
            for (IngredienteDTO ingDto : data.getIngredientes()) {
                if (ingDto == null) continue;
                if (ingDto.getCodigo() != null) {
                    Optional<Ingrediente> opt = ingredienteRepo.findById(ingDto.getCodigo());
                    if (opt.isPresent()) {
                        Ingrediente ing = opt.get();
                        // si el DTO trae cantidad la asignamos (siguiendo patrón de OrdenCompra)
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
            r.setIngredientes(ingList);
        }

        repo.save(r);
        return 0;
    }

    public List<RecetaDTO> getAll() {
        return repo.findAll().stream().map(r -> {
            RecetaDTO dto = new RecetaDTO();
            dto.setRecetaId(r.getRecetaId());
            dto.setNombre(r.getNombre());
            dto.setDescripcion(r.getDescripcion());
            dto.setTiempoPreparacion(r.getTiempoPreparacion());
            dto.setImagen(r.getImagen());

            if (r.getIngredientes() != null) {
                List<IngredienteDTO> ingDtos = r.getIngredientes().stream()
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
        }).collect(Collectors.toList());
    }

    public RecetaDTO getById(Integer id) {
        return repo.findById(id).map(r -> {
            RecetaDTO dto = new RecetaDTO();
            dto.setRecetaId(r.getRecetaId());
            dto.setNombre(r.getNombre());
            dto.setDescripcion(r.getDescripcion());
            dto.setTiempoPreparacion(r.getTiempoPreparacion());
            dto.setImagen(r.getImagen());

            if (r.getIngredientes() != null) {
                List<IngredienteDTO> ingDtos = r.getIngredientes().stream()
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

    public int updateById(Integer id, RecetaDTO newData) {
        Optional<Receta> opt = repo.findById(id);
        if (opt.isEmpty()) return 1;
        Receta r = opt.get();

        if (newData.getNombre() != null) r.setNombre(newData.getNombre());
        if (newData.getDescripcion() != null) r.setDescripcion(newData.getDescripcion());
        if (newData.getTiempoPreparacion() != null) r.setTiempoPreparacion(newData.getTiempoPreparacion());
        if (newData.getImagen() != null) r.setImagen(newData.getImagen());

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
            r.setIngredientes(ingList);
        }

        repo.save(r);
        return 0;
    }

    public int deleteById(Integer id) {
        return repo.findById(id).map(r -> { repo.delete(r); return 0; }).orElse(1);
    }

    public boolean exist(Integer id) { return repo.existsById(id); }
    public long count() { return repo.count(); }
}
