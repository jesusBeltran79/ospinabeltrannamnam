package co.edu.unbosque.ospinabeltrannamnam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.ospinabeltrannamnam.model.DireccionEmpresa;

@Repository
public interface DireccionEmpresaRepository extends JpaRepository<DireccionEmpresa, Integer> { }
