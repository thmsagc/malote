package com.es.maloteapi.service;

import com.es.maloteapi.entity.Conta;
import com.es.maloteapi.entity.Renda;
import com.es.maloteapi.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RecorrenciaService {

    private ContaRepository contaRepository;

    public RecorrenciaService (ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public void processarRecorrencias(long dias, long meses, long anos, List<Conta> contas) {
        contas.forEach(conta -> {
            conta.getRendas().forEach(renda -> {
                if(renda.getRecorrencia().equals(Renda.RECORRENCIA_DIARIA))
                    conta.setSaldoAtual(conta.getSaldoAtual().add(renda.getValor().multiply(BigDecimal.valueOf(dias))));
                if(renda.getRecorrencia().equals(Renda.RECORRENCIA_MENSAL))
                    conta.setSaldoAtual(conta.getSaldoAtual().add(renda.getValor().multiply(BigDecimal.valueOf(meses))));
                if(renda.getRecorrencia().equals(Renda.RECORRENCIA_ANUAL))
                    conta.setSaldoAtual(conta.getSaldoAtual().add(renda.getValor().multiply(BigDecimal.valueOf(anos))));
            });
            contaRepository.save(conta);
        });
    }
}
