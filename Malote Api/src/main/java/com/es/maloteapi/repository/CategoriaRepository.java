package com.es.maloteapi.repository;

import com.es.maloteapi.entity.Usuario;
import com.es.maloteapi.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findAllByUsuario(Usuario usuario);
}
