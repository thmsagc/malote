package com.es.maloteapi.service;

import com.es.maloteapi.entity.Categoria;
import com.es.maloteapi.entity.Conta;
import com.es.maloteapi.entity.Despesa;
import com.es.maloteapi.repository.DespesaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DespesaService {

    private final DespesaRepository despesaRepository;

    public DespesaService(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }

    public List<Despesa> findAllByDataBetween(LocalDate inicio, LocalDate fim) {
        return despesaRepository.findAllByDataBetween(inicio, fim);
    }

    public List<Despesa> findAllByCategoria(Categoria categoria) {
        return despesaRepository.findAllByCategoria(categoria);
    }

    public List<Despesa> findAllByConta(Conta conta) {
        return despesaRepository.findAllByConta(conta);
    }
}
