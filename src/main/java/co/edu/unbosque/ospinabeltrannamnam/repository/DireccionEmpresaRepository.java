package co.edu.unbosque.ospinabeltrannamnam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.ospinabeltrannamnam.model.DireccionEmpresa;
import java.util.List;

@Repository
public interface DireccionEmpresaRepository extends JpaRepository<DireccionEmpresa, Integer> {
    List<DireccionEmpresa> findByEmpresa_Nit(Integer nit);
    List<DireccionEmpresa> findByEmpresaNit(Integer empresaNit);

}
