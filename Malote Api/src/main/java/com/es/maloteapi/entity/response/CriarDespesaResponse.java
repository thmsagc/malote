package com.es.maloteapi.entity.response;

import com.es.maloteapi.entity.Despesa;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CriarDespesaResponse {
    private Long id;
    private Long conta;
    private String nome;
    private Long categoria;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;

    public static CriarDespesaResponse from(Despesa despesa) {
        CriarDespesaResponse criarDespesaResponse = new CriarDespesaResponse();
        criarDespesaResponse.setId(despesa.getId());
        criarDespesaResponse.setConta(despesa.getConta().getId());
        criarDespesaResponse.setNome(despesa.getNome());
        criarDespesaResponse.setCategoria(despesa.getCategoria().getId());
        criarDespesaResponse.setDescricao(despesa.getDescricao());
        criarDespesaResponse.setValor(despesa.getValor());
        criarDespesaResponse.setData(despesa.getData());
        return criarDespesaResponse;
    }
}
