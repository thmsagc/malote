package com.es.maloteapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Renda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    public static final String RECORRENCIA_DIARIA = "diaria";
    public static final String RECORRENCIA_MENSAL = "mensal";
    public static final String RECORRENCIA_ANUAL = "anual";
    public static final String SEM_RECORRENCIA = "nenhuma";

    private String nome;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private String recorrencia;
}
