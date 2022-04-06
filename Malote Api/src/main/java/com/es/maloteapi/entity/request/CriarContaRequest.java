package com.es.maloteapi.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarContaRequest {
    private String nome;
    private Boolean criarCategoriasPadrao;
    private BigDecimal saldoInicial;
}
