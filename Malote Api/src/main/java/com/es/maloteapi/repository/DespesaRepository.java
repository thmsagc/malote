package com.es.maloteapi.repository;

import com.es.maloteapi.entity.Categoria;
import com.es.maloteapi.entity.Conta;
import com.es.maloteapi.entity.Despesa;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findAllByConta(Conta conta);
    List<Despesa> findAllByCategoria(Categoria categoria);
    List<Despesa> findAllByDataBetween(LocalDate inicio, LocalDate fim);
}
