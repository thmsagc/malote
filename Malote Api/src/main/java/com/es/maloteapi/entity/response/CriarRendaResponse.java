package com.es.maloteapi.entity.response;

import com.es.maloteapi.entity.Renda;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CriarRendaResponse {
    private Long id;
    private Long conta;
    private String nome;
    private Long categoria;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;

    public static CriarRendaResponse from(Renda renda) {
        CriarRendaResponse criarRendaResponse = new CriarRendaResponse();
        criarRendaResponse.setId(renda.getId());
        criarRendaResponse.setConta(renda.getConta().getId());
        criarRendaResponse.setNome(renda.getNome());
        criarRendaResponse.setCategoria(renda.getCategoria().getId());
        criarRendaResponse.setDescricao(renda.getDescricao());
        criarRendaResponse.setValor(renda.getValor());
        criarRendaResponse.setData(renda.getData());
        return criarRendaResponse;
    }
}
