package com.es.maloteapi.repository;

import com.es.maloteapi.entity.Categoria;
import com.es.maloteapi.entity.Conta;
import com.es.maloteapi.entity.Despesa;
import com.es.maloteapi.entity.Renda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RendaRepository extends JpaRepository<Renda, Long> {
    List<Renda> findAllByConta(Conta conta);
    List<Renda> findAllByCategoria(Categoria categoria);
    List<Renda> findAllByDataBetween(LocalDate inicio, LocalDate fim);
}
