package com.es.maloteapi.entity.response;

import com.es.maloteapi.entity.Renda;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CriarRendaResponse {
    private Long id;
    private Long conta;
    private String nome;
    private Long categoria;
    private String descricao;
    private BigDecimal valor;
    private String data;
    private String recorrencia;

    public static CriarRendaResponse from(Renda renda) {
        CriarRendaResponse criarRendaResponse = new CriarRendaResponse();
        criarRendaResponse.setId(renda.getId());
        criarRendaResponse.setConta(renda.getConta().getId());
        criarRendaResponse.setNome(renda.getNome());
        criarRendaResponse.setCategoria(renda.getCategoria().getId());
        criarRendaResponse.setDescricao(renda.getDescricao());
        criarRendaResponse.setValor(renda.getValor());
        criarRendaResponse.setData(StringUtils.localDateToStringDdMmYyyy(renda.getData()));
        criarRendaResponse.setRecorrencia(StringUtils.localDateToStringDdMmYyyy(renda.getData()));
        return criarRendaResponse;
    }
}
