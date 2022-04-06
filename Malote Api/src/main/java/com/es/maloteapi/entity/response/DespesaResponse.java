package com.es.maloteapi.entity.response;

import com.es.maloteapi.entity.Despesa;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class DespesaResponse {
    private Long id;
    private Long categoria;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;

    public static DespesaResponse from(Despesa despesa) {
        DespesaResponse despesaResponse = new DespesaResponse();
        despesaResponse.setId(despesa.getId());
        despesaResponse.setCategoria(despesa.getCategoria().getId());
        despesaResponse.setNome(despesa.getNome());
        despesaResponse.setDescricao(despesa.getDescricao());
        despesaResponse.setValor(despesa.getValor());
        despesaResponse.setData(despesa.getData());
        return despesaResponse;
    }

    public static List<DespesaResponse> from(List<Despesa> despesaList) {
        List<DespesaResponse> despesaResponseList = new ArrayList<>();
        despesaList.forEach(despesa -> {
            despesaResponseList.add(from(despesa));
        });
        return despesaResponseList;
    }
}
