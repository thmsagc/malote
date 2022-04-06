package com.es.maloteapi.entity.response;

import com.es.maloteapi.entity.Conta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CriarContaResponse {
    private Long id;
    private String nome;
    private BigDecimal saldoInicial;

    public static CriarContaResponse from(Conta conta) {
        CriarContaResponse contaResponse = new CriarContaResponse();
        contaResponse.setId(conta.getId());
        contaResponse.setNome(conta.getNome());
        contaResponse.setSaldoInicial(conta.getSaldo());
        return contaResponse;
    }
}
