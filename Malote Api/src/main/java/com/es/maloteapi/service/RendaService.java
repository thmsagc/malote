package com.es.maloteapi.service;

import com.es.maloteapi.entity.Categoria;
import com.es.maloteapi.entity.Conta;
import com.es.maloteapi.entity.Renda;
import com.es.maloteapi.repository.RendaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RendaService {

    private final RendaRepository rendaRepository;

    public RendaService(RendaRepository rendaRepository) {
        this.rendaRepository = rendaRepository;
    }

    public List<Renda> findAllByDataBetween(LocalDate inicio, LocalDate fim) {
        return rendaRepository.findAllByDataBetween(inicio, fim);
    }

    public List<Renda> findAllByCategoria(Categoria categoria) {
        return rendaRepository.findAllByCategoria(categoria);
    }

    public List<Renda> findAllByConta(Conta conta) {
        return rendaRepository.findAllByConta(conta);
    }
}
