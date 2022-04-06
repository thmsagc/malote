package com.es.maloteapi.repository;

import com.es.maloteapi.entity.Usuario;
import com.es.maloteapi.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    List<Conta> findAllByUsuario(Usuario usuario);
}
