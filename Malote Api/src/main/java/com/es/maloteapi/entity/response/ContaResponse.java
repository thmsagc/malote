package com.es.maloteapi.entity.response;

import com.es.maloteapi.entity.Conta;
import com.es.maloteapi.entity.Renda;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ContaResponse {
    private Long id;
    private String nome;
    private BigDecimal saldoInicial;
    private BigDecimal saldoAtual;
    private List<RendaResponse> rendas;
    private List<DespesaResponse> despesas;

    
    public static ContaResponse from(Conta conta) {
        ContaResponse contaResponse = new ContaResponse();
        contaResponse.setId(conta.getId());
        contaResponse.setNome(conta.getNome());
        contaResponse.setSaldoInicial(conta.getSaldoInicial());
        contaResponse.setSaldoAtual(conta.getSaldoAtual());
        contaResponse.setRendas(RendaResponse.from(conta.getRendas()));
        contaResponse.setDespesas(DespesaResponse.from(conta.getDespesas()));
        return contaResponse;
    }

    public static List<ContaResponse> from(List<Conta> contas) {
        List<ContaResponse> contaResponseList = new ArrayList<>();
        contas.forEach(conta -> {
            contaResponseList.add(from(conta));
        });
        return contaResponseList;
    }
}
