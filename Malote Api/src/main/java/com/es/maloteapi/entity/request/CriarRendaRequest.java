package com.es.maloteapi.entity.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CriarRendaRequest {
    private Long conta;
    private String nome;
    private String descricao;
    private Long categoria;
    private BigDecimal valor;
    private String data;
    private String recorrencia;
}
