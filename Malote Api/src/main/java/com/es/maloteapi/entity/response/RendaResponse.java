package com.es.maloteapi.entity.response;

import com.es.maloteapi.entity.Renda;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class RendaResponse {
    private Long id;
    private Long categoria;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;

    public static RendaResponse from(Renda renda) {
        RendaResponse rendaResponse = new RendaResponse();
        rendaResponse.setId(renda.getId());
        rendaResponse.setCategoria(renda.getCategoria().getId());
        rendaResponse.setNome(renda.getNome());
        rendaResponse.setDescricao(renda.getDescricao());
        rendaResponse.setValor(renda.getValor());
        rendaResponse.setData(renda.getData());
        return rendaResponse;
    }

    public static List<RendaResponse> from(List<Renda> rendaList) {
        List<RendaResponse> rendaResponseList = new ArrayList<>();
        rendaList.forEach(renda -> {
            rendaResponseList.add(from(renda));
        });
        return rendaResponseList;
    }
}
