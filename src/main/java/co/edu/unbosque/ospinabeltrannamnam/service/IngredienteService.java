package co.edu.unbosque.ospinabeltrannamnam.service;

import java.util.List;
import java.util.Optional;

import co.edu.unbosque.ospinabeltrannamnam.dto.IngredienteDTO;

public interface IngredienteService {
    IngredienteDTO crear(IngredienteDTO dto);
    Optional<IngredienteDTO> obtenerPorId(Integer id);
    List<IngredienteDTO> listarTodos();
    IngredienteDTO actualizar(Integer id, IngredienteDTO dto);
    void eliminar(Integer id);
}
