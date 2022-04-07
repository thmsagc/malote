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
    List<Renda> findAllByContaAndCategoria(Conta conta, Categoria categoria);
    List<Renda> findAllByContaAndRecorrencia(Conta conta, String recorrencia);
    List<Renda> findAllByContaAndDataBetween(Conta conta, LocalDate inicio, LocalDate fim);
}
