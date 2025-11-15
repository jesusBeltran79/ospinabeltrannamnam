package co.edu.unbosque.ospinabeltrannamnam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.ospinabeltrannamnam.model.Receta;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Integer> { }
